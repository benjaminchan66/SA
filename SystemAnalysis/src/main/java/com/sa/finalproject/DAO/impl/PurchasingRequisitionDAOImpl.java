package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.sa.finalproject.DAO.PurchasingRequisitionDAO;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

public class PurchasingRequisitionDAOImpl implements PurchasingRequisitionDAO {
	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String insert(PurchaseOrder mixedOrder, long employeeID) {
		// TODO Auto-generated method stub
		String supplierLevel = "";

		// divide the supplier from the mixed order
		ArrayList<PurchaseOrder> dividedPurchaseList = new ArrayList<PurchaseOrder>();

		for (int i = 0; i < mixedOrder.getList().size(); i++) {
			PurchasedProduct currentProduct = mixedOrder.getList().get(i);
			long currentSupplierID = currentProduct.getSupplierID();
			boolean supplierIDIsInTheList = false;

			for (int j = 0; j < dividedPurchaseList.size(); j++) {
				PurchaseOrder currentList = dividedPurchaseList.get(j);
				if (currentSupplierID == currentList.getSupplierID()) {
					// the current product belongs to same producer, add this
					// product to the divided list
					currentList.add(currentProduct);
					supplierIDIsInTheList = true;
					break;
				}
			}

			if (supplierIDIsInTheList == false) {
				// if this supplier is not in the divided list, then create a
				// new list for this supplier
				PurchaseOrder newPR = new PurchaseOrder(currentSupplierID);
				newPR.add(currentProduct);
				dividedPurchaseList.add(newPR);
				System.out.println("Add the supplier whose id is " + currentSupplierID);
			}
		}

		// Accessing Tables: PR, PR_confirm, Product_connect_PR,
		// PR_supplier_grade

		String sql = "INSERT INTO PurchasingRequisition(employee_id, time) VALUES(?, Now())";
		String sql2 = "INSERT INTO PR_confirm(PR_serial) VALUES(?)";
		String sql3 = "INSERT INTO Product_connect_PR(product_id, PR_serial, price_att, quantity) VALUES(?, ?, ?, ?)";
		String sql4 = "INSERT INTO PR_supplier_grade(PR_serial, supplier_id, grade_att) VALUES(?, ?, ?)";

		try {
			System.out.println("The length of divided order is " + dividedPurchaseList.size());
			conn = dataSource.getConnection();
			for (int i = 0; i < dividedPurchaseList.size(); i++) {
				PurchaseOrder currentListOrder = dividedPurchaseList.get(i);
				conn.setAutoCommit(false);

				// First SQL command should keep the generated key, Entity :
				// PurchasingRequisition
				smt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				smt.setLong(1, employeeID);
				smt.executeUpdate();
				this.rs = smt.getGeneratedKeys();
				long newPRSerial = 0;
				if (rs.next()) {
					newPRSerial = rs.getLong(1);
					System.out.println("The serial is " + newPRSerial);
				}
				rs.close();
				smt.close();

				// Second SQL command, Entity : PR_confirm, if the supplier
				// level isn't A class, it won't be sent until the manager
				// confirms it.
				// search the level in the database
				String sql2_1 = "SELECT * FROM Supplier WHERE supplier_id = ?";
				smt = conn.prepareStatement(sql2_1);
				smt.setLong(1, currentListOrder.getSupplierID());
				ResultSet rs1 = smt.executeQuery();

				if (rs1.next()) {
					supplierLevel = rs1.getString("supplier_level");
					System.out.println("Supplier name : " + rs1.getString("supplier_name"));
					System.out.println("Supplier level : " + supplierLevel);
				}
				rs1.close();
				smt.close();

				// insert the requisition into PR_confirm
				if ("A".equals(supplierLevel)) {
					// the default value of confirming is true

					sql2 = "INSERT INTO PR_confirm(PR_serial, PR_confirm, employee_id, time) VALUES(?, ?, ?, Now())";
					this.smt = conn.prepareStatement(sql2);
					smt.setLong(1, newPRSerial);
					smt.setBoolean(2, true);
					smt.setLong(3, employeeID);
					smt.executeUpdate();
					smt.close();
				} else {
					smt = conn.prepareStatement(sql2);
					smt.setLong(1, newPRSerial);
					smt.executeUpdate();
					smt.close();
				}

				// Third SQL command, Entity : Product_connect_PR, create the
				// record in Product_connect_PR for each item
				for (int j = 0; j < currentListOrder.getList().size(); j++) {
					PurchasedProduct currentProduct = currentListOrder.getList().get(j);
					smt = conn.prepareStatement(sql3);
					smt.setLong(1, currentProduct.getProductID());
					smt.setLong(2, newPRSerial);
					smt.setInt(3, currentProduct.getProductPrice());
					smt.setInt(4, currentProduct.getPurchasingAmount());

					smt.executeUpdate();

					smt.close();
				}

				// Forth SQL command, Entity : PR_supplier_grade
				// String sql4 = "INSERT INTO PR_supplier_grade(PR_serial,
				// supplier_id, grade_att) VALUES(?, ?, ?)";
				smt = conn.prepareStatement(sql4);
				smt.setLong(1, newPRSerial);
				smt.setLong(2, currentListOrder.getSupplierID());
				smt.setString(3, supplierLevel);
				smt.executeUpdate();
				smt.close();

				conn.commit();
				conn.setAutoCommit(true);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					// cancel all the changes that we did to the database
					System.err.println("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					e.printStackTrace();
				}
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

		return supplierLevel;

	}

	@Override
	public void confirm(long managerID, long aRequisitionSerial, boolean isConfirmed) {
		// TODO Auto-generated method stub

		String sql = "UPDATE PR_confirm SET PR_confirm = ?, employee_id = ?, time = Now() WHERE PR_serial = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);

			smt.setBoolean(1, isConfirmed);
			smt.setLong(2, managerID);
			smt.setLong(3, aRequisitionSerial);

			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}

	}

	@Override
	public ArrayList<PurchasingRequisition> getList() {
		// TODO Auto-generated method stub

		// TABLES : Purchasing requisition, PR_confirm

		ArrayList<PurchasingRequisition> requisitionList = new ArrayList<PurchasingRequisition>();
		ArrayList<Serial> serials = new ArrayList<Serial>();

		String sql = "SELECT PR_serial FROM PurchasingRequisition";

		try {
			conn = this.dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			this.rs = smt.executeQuery();
			while (rs.next()) {
				long serial = rs.getLong("PR_serial");
				serials.add(new Serial(serial));
			}

			rs.close();
			smt.close();

			for (int i = 0; i < serials.size(); i++) {
				// get each requisition form the database
				requisitionList.add(this.get(serials.get(i).getSerial()));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}

		return requisitionList;
	}

	@Override
	public PurchasingRequisition get(long aRequisitionSerial) {
		// TODO Auto-generated method stub

		PurchasingRequisition requisition = new PurchasingRequisition();

		// get requisition info
		String sql = "SELECT * FROM PurchasingRequisition WHERE PR_serial = ?";
		String sql2 = "SELECT * FROM PR_confirm WHERE PR_serial = ?";
		try {
			conn = dataSource.getConnection();

			// first SQL command, from PurchasingRequisition
			smt = conn.prepareStatement(sql);

			smt.setLong(1, aRequisitionSerial);

			this.rs = smt.executeQuery();
			if (rs.next()) {
				// set the value for the requisition
				requisition.setPrSerial(rs.getLong("PR_serial"));
				requisition.setEmployeeID(rs.getLong("employee_id"));
				requisition.setDate(rs.getDate("time"));
			}

			rs.close();
			smt.close();

			// second SQL command, from PR_confirm
			smt = conn.prepareStatement(sql2);
			smt.setLong(1, aRequisitionSerial);

			this.rs = smt.executeQuery();
			if (rs.next()) {
				requisition.setConfirmed(rs.getBoolean("PR_confirm"));
				requisition.setJudgementManagerID(rs.getLong("employee_id"));
				requisition.setConfirmingDate(rs.getDate("time"));
			}

			rs.close();
			smt.close();

			// third SQL command, from PR_supplier_grade
			Supplier requisitionSupplier = new Supplier();
			String sql3 = "SELECT * FROM PR_supplier_grade WHERE PR_serial = ?";
			smt = conn.prepareStatement(sql3);

			smt.setLong(1, aRequisitionSerial);

			this.rs = smt.executeQuery();
			if (rs.next()) {
				requisitionSupplier.setSupplierID(rs.getLong("supplier_id"));
				requisitionSupplier.setLevel(rs.getString("grade_att"));
			}

			rs.close();
			smt.close();

			// forth SQL command, search the supplier name from Supplier entity
			String sql4 = "SELECT * FROM Supplier WHERE supplier_id = ?";
			smt = conn.prepareStatement(sql4);
			smt.setLong(1, requisitionSupplier.getSupplierID());

			this.rs = smt.executeQuery();
			if (rs.next()) {
				requisitionSupplier.setSupplierName(rs.getString("supplier_name"));
				requisitionSupplier.setPhone(rs.getString("supplier_phone"));
				requisitionSupplier.setAddress(rs.getString("supplier_address"));
			}

			requisition.setSupplier(requisitionSupplier);
			rs.close();
			smt.close();
			
			PurchaseOrder content = new PurchaseOrder();
			content = this.getContentOf(aRequisitionSerial);
			requisition.setRequisitionContent(content);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}

		return requisition;
	}

	@Override
	public ArrayList<PurchasingRequisition> getUnconfirmedOrders() {
		ArrayList<PurchasingRequisition> requisitionList = new ArrayList<PurchasingRequisition>();
		ArrayList<Serial> serialList = new ArrayList<Serial>();
		String sql = "SELECT * FROM PR_confirm WHERE PR_confirm = NULL";

		try {
			this.conn = this.dataSource.getConnection();
			smt = conn.prepareStatement(sql);

			rs = smt.executeQuery();

			while (rs.next()) {
				serialList.add(new Serial(rs.getLong("PR_serial")));
			}
			rs.close();
			smt.close();

			for (int i = 0; i < serialList.size(); i++) {
				long currentSerial = serialList.get(i).getSerial();
				requisitionList.add(this.get(currentSerial));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}

		return requisitionList;
	}

	public Supplier getASupplierOf(long prSerial) {
		Supplier supplier = new Supplier();
		String sql = "SELECT * FROM PR_supplier_grade WHERE PR_serial = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, prSerial);

			rs = smt.executeQuery();

			if (rs.next()) {
				long supplierID = rs.getLong("supplier_id");
				supplier.setSupplierID(supplierID);
				String supplierLevel = rs.getString("grade_att");
				supplier.setLevel(supplierLevel);
			}

			rs.close();
			smt.close();

			String sql2 = "SELECT * FROM Supplier WHERE supplier_id = ?";
			smt = conn.prepareStatement(sql2);
			smt.setLong(1, supplier.getSupplierID());
			rs = smt.executeQuery();
			if (rs.next()) {
				supplier.setSupplierName(rs.getString("supplier_name"));
				supplier.setPhone(rs.getString("supplier_phone"));
				supplier.setAddress(rs.getString("supplier_address"));
				supplier.setLevel(rs.getString("supplier_level"));
			}

			rs.close();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}

		return supplier;
	}
	
	
	// 取得單一請購單的內容物
	public PurchaseOrder getContentOf(long prSerial) {
		PurchaseOrder content = new PurchaseOrder();
		String sql = "SELECT * FROM Product_connect_PR WHERE PR_serial = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, prSerial);
			rs = smt.executeQuery();
			
			while(rs.next()) {
				PurchasedProduct currentItem = new PurchasedProduct();
				currentItem.setProductID(rs.getLong("product_id"));
				currentItem.setProductPrice(rs.getInt("price_att"));
				currentItem.setPurchasingAmount(rs.getInt("quantity"));
				content.getList().add(currentItem);
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// do nothing
			}
		}
		
		return content;
	}

}

// 輔助類別
class Serial {
	private long serial = 0;

	public Serial(long serial) {
		this.serial = serial;
	}

	public void setSerial(long newSerial) {
		this.serial = newSerial;
	}

	public long getSerial() {
		return this.serial;
	}
}