package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.sa.finalproject.DAO.WarehouseWarrantDAO;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.WarehouseWarrant;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

public class WarehouseWarrantDAOImpl implements WarehouseWarrantDAO{
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(BillOfPurchase bop, long employeeID) {
		
		String sql = "INSERT INTO WarehouseWarrant(WW_serial, employee_id, time, supplier_id) VALUES(?, ?, Now(), ?)";
		String sql2 = "INSERT INTO Product_connectWW(product_id, WW_serial, quantity) VALUES(?, ?, ?)";
		try {
			conn = dataSource.getConnection();
			
			// SQL_1
			String sql_1 = "SELECT * FROM PR_supplier_grade WHERE PR_serial = ?";
			smt = conn.prepareStatement(sql_1);
			smt.setLong(1, bop.getBopSerial());
			rs = smt.executeQuery();
			long supplierID = 0;
			if(rs.next()) {
				supplierID = rs.getLong("supplier_id");
			}
			rs.close();
			smt.close();
			
			// SQL
			smt = conn.prepareStatement(sql);
			smt.setLong(1, bop.getBopSerial());
			smt.setLong(2, employeeID);
			smt.setLong(3, supplierID);
			smt.executeUpdate();
			 
			smt.close();
			
			//second SQL command
			String sql2_1 = "UPDATE Product SET inventory = ? WHERE product_id = ?";
			PurchaseOrder bopContent = bop.getBopContent();
			for(int i = 0; i < bopContent.getList().size(); i++) {
				PurchasedProduct currentItem = bopContent.getList().get(i);
				smt = conn.prepareStatement(sql2);
				smt.setLong(1, currentItem.getProductID());
				smt.setLong(2, bop.getBopSerial());
				smt.setInt(3, currentItem.getPurchasingAmount());
				smt.executeUpdate();
				smt.close();
				
				// update the inventory of specific product
				smt = conn.prepareStatement(sql2_1);
				smt.setInt(1, currentItem.getPurchasingAmount());
				smt.setLong(2, currentItem.getProductID());
				smt.executeUpdate();
				smt.close();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public ArrayList<WarehouseWarrant> getList() {
		String sql = "SELECT * FROM WarehouseWarrant";
		ArrayList<WarehouseWarrant> wwList = new ArrayList<WarehouseWarrant>(); 
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			ArrayList<Long> serialList = new ArrayList<Long>();
			rs = smt.executeQuery();
			while(rs.next()) {
				serialList.add(rs.getLong("WW_serial"));
			}
			rs.close();
			smt.close();
			
			for(int i = 0; i < serialList.size(); i++) {
				WarehouseWarrant currentWW = this.get(serialList.get(i));
				wwList.add(currentWW);
			}
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return wwList;
	}

	@Override
	public WarehouseWarrant get(long WW_serial) {
		String sql = "SELECT * FROM WarehouseWarrant WHERE WW_serial = ?";
		WarehouseWarrant ww = new WarehouseWarrant();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, WW_serial);
			rs = smt.executeQuery();
			
			if(rs.next()){
				ww.setWwSerial(rs.getLong("WW_serial"));
				ww.setEmployeeID(rs.getLong("Employee_id"));
				ww.setDate(rs.getDate("time"));
				ww.setSupplierID(rs.getLong("Supplier_id"));
			}
			rs.close();
			smt.close();
			
			PurchaseOrder content = new PurchaseOrder();
			content = this.getContentOf(WW_serial);
			ww.setWwContent(content);
			
			
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return ww;
	}
	
	
	// 取得單一入庫單的內容物
		public PurchaseOrder getContentOf(long wwSerial) {
			PurchaseOrder content = new PurchaseOrder();
			String sql = "SELECT * FROM Product_connect_WW WHERE PR_serial = ?";
			try {
				conn = dataSource.getConnection();
				smt = conn.prepareStatement(sql);
				smt.setLong(1, wwSerial);
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
