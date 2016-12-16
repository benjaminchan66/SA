package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;



import com.sa.finalproject.DAO.BillOfPurchaseDAO;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;

import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

public class BillOfPurchaseDAOImpl implements BillOfPurchaseDAO {
	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<BillOfPurchase> getList() { // 列出所有進貨單
		String sql = "SELECT * FROM BillOfPurchase";
		return getList(sql);
	}

	public BillOfPurchase get(long bopserial) {
		// 取得單一進貨單資料
		String sql = "SELECT * FROM BillOfPurchase WHERE BOP_serial = ?";
		BillOfPurchase bop = new BillOfPurchase();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, bopserial);
			rs = smt.executeQuery();
			if (rs.next()) {
				bop.setBopSerial(rs.getInt("BOP_serial"));
				bop.setEmployeeID(rs.getLong("employee_id"));
				bop.setDateTime(rs.getDate("time"));
				bop.setHasArrived(rs.getBoolean("has_arrived"));
				bop.setTotalAmount(rs.getInt("total_amount"));
				bop.setAccountantId(rs.getInt("accountant_id"));
				bop.setHasPaid(rs.getBoolean("has_paid"));
				bop.setPassed(rs.getBoolean("passed"));
				bop.setRemarks(rs.getString("remark"));
			}
			rs.close();
			smt.close();

			PurchaseOrder content = new PurchaseOrder();

			content = this.getContentOf(bopserial);

			bop.setBopContent(content);

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return bop;
	}

	public void examineGoods(long bopSerial, boolean passed) { // 驗貨
		String sql = "UPDATE BillOfPurchase SET passed=?, has_arrived = ? WHERE  BOP_serial= ? ";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, passed);
			smt.setBoolean(2, true);
			smt.setLong(3, bopSerial);
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void transferIntoBOP(PurchasingRequisition aPurchaseingRequisition) {
		String sql = "INSERT INTO BillOfPurchase (BOP_serial, employee_id, time, has_arrived, total_amount, accountant_id, has_paid, passed) VALUES(?,?,now(),false,?, null, false,false)";
		String sql2 = "INSERT INTO Product_connect_BOP(product_id, BOP_serial, price_att, quantity) VALUES(?, ?, ?, ?)";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, aPurchaseingRequisition.getPrSerial());
			smt.setLong(2, aPurchaseingRequisition.getEmployeeID());
			smt.setInt(3, aPurchaseingRequisition.getRequisitionContent().getListPrice());
			smt.executeUpdate();
			smt.close();

			// second SQL command
			PurchaseOrder orderContent = aPurchaseingRequisition.getRequisitionContent();
			for (int i = 0; i < orderContent.getList().size(); i++) {
				PurchasedProduct currentItem = orderContent.getList().get(i);
				smt = conn.prepareStatement(sql2);
				smt.setLong(1, currentItem.getProductID());
				smt.setLong(2, aPurchaseingRequisition.getPrSerial());
				smt.setInt(3, currentItem.getProductPrice());
				smt.setInt(4, currentItem.getPurchasingAmount());
				smt.executeUpdate();
				smt.close();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	public ArrayList<BillOfPurchase> getList(String sql) {
		ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		// will need this part later

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while (rs.next()) {
				BillOfPurchase aBillOfPurchase = new BillOfPurchase(0, 0, null, false, 0, 0, false, false, "");
				aBillOfPurchase.setBopSerial(rs.getInt("BOP_serial"));
				aBillOfPurchase.setEmployeeID(rs.getLong("employee_id"));
				aBillOfPurchase.setDateTime(rs.getDate("time"));
				aBillOfPurchase.setHasArrived(rs.getBoolean("has_arrived"));
				aBillOfPurchase.setTotalAmount(rs.getInt("total_amount"));
				aBillOfPurchase.setAccountantId(rs.getInt("accountant_id"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("has_paid"));
				aBillOfPurchase.setPassed(rs.getBoolean("passed"));
				aBillOfPurchase.setRemarks(rs.getString("remark"));
				billOfPurchaseList.add(aBillOfPurchase);

			}
			rs.close();
			smt.close();

			String sql2 = "SELECT * FROM Product_connect_BOP WHERE BOP_serial = ?";
			for (int i = 0; i < billOfPurchaseList.size(); i++) {
				BillOfPurchase currentBill = billOfPurchaseList.get(i);
				smt = conn.prepareStatement(sql2);
				smt.setLong(1, currentBill.getBopSerial());
				rs = smt.executeQuery();

				while (rs.next()) {
					// the product name hasn't been set into the object.
					PurchasedProduct aProduct = new PurchasedProduct();
					aProduct.setProductID(rs.getLong("product_id"));
					aProduct.setProductPrice(rs.getInt("price_att"));
					aProduct.setPurchasingAmount(rs.getInt("quantity"));
					currentBill.getBopContent().getList().add(aProduct);
				}
				rs.close();
				smt.close();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return billOfPurchaseList;
	}

	public ArrayList<BillOfPurchase> getList(long bopSerial) { // 列出所有進貨單方法
		String sql = "SELECT * FROM BillOfPurchase where BOP_serial = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, bopSerial);
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return getList(sql);
	}

	public ArrayList<BillOfPurchase> showUnpaidProduct() { // 顯示到貨且未付款商品
		String sql = "SELECT * from BillOfPurchase WHERE has_arrived = ? and has_paid = ?";
		ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, true);
			smt.setBoolean(2, false);
			rs = smt.executeQuery();
			while (rs.next()) {
				BillOfPurchase aBillOfPurchase = new BillOfPurchase(0, 0, null, false, 0, 0, false, false, "");
				aBillOfPurchase.setBopSerial(rs.getLong("BOP_serial"));
				aBillOfPurchase.setEmployeeID(rs.getLong("employee_id"));
				aBillOfPurchase.setDateTime(rs.getDate("time"));
				aBillOfPurchase.setHasArrived(rs.getBoolean("has_arrived"));
				aBillOfPurchase.setTotalAmount(rs.getInt("total_amount"));
				aBillOfPurchase.setAccountantId(rs.getInt("accountant_id"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("has_paid"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("passed"));
				aBillOfPurchase.setRemarks(rs.getString("remark"));
				billOfPurchaseList.add(aBillOfPurchase);
			}
			rs.close();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return billOfPurchaseList;
	}

	public void paid(long bopSerial) { // 付款
		String sql = "UPDATE BillOfPurchase SET has_paid=?" + "WHERE  BOP_Serial= ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, true);
			smt.setLong(2, bopSerial);
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void update(long serial, BillOfPurchase aBillOfPurchase) { // 更新資料
		String sql = "UPDATE BillOfPurchase SET has_arrived=?,passed=? ,has_paid= ? ,remark=? WHERE BOP_serial = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, aBillOfPurchase.isHasArrived());
			smt.setBoolean(2, aBillOfPurchase.isPassed());
			smt.setBoolean(3, aBillOfPurchase.isHasPaid());
			smt.setString(4, aBillOfPurchase.getRemarks());
			smt.setLong(5, serial);
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	// 取得單一進貨單之內容物
	public PurchaseOrder getContentOf(long bopSerial) {
		PurchaseOrder content = new PurchaseOrder();
		String sql = "SELECT * FROM Product_connect_BOP WHERE BOP_serial = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, bopSerial);
			rs = smt.executeQuery();

			while (rs.next()) {
				PurchasedProduct currentItem = new PurchasedProduct();
				currentItem.setProductID(rs.getLong("product_id"));
				currentItem.setProductPrice(rs.getInt("price_att"));
				currentItem.setPurchasingAmount(rs.getInt("quantity"));
				content.getList().add(currentItem);
			}

			rs.close();
			smt.close();

			String sql2 = "SELECT * FROM Product WHERE product_id = ?";

			for (int i = 0; i < content.getList().size(); i++) {
				PurchasedProduct currentItem = content.getList().get(i);
				smt = conn.prepareStatement(sql2);
				smt.setLong(1, currentItem.getProductID());
				rs = smt.executeQuery();

				String productName = "";
				if (rs.next()) {
					productName = rs.getString("product_name");
				}

				currentItem.setProductName(productName);

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

	public ArrayList<BillOfPurchase> getRemarkedList() {
		ArrayList<BillOfPurchase> bopList = new ArrayList<BillOfPurchase>();

		try {
			String sql = "SELECT * FROM BillOfPurchase WHERE remark IS NOT NULL";

			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);

			rs = smt.executeQuery();

			ArrayList<Long> serialList = new ArrayList<Long>();
			while (rs.next()) {
				serialList.add(rs.getLong("BOP_serial"));
			}

			rs.close();
			smt.close();

			for (int i = 0; i < serialList.size(); i++) {
				long bopserial = serialList.get(i);
				BillOfPurchase bop = this.get(bopserial);
				bopList.add(bop);
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

		return bopList;
	}

}
