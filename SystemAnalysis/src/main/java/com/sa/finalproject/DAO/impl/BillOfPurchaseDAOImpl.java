package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.sa.finalproject.DAO.BillOfPurchaseDAO;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Remark;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;

public class BillOfPurchaseDAOImpl implements BillOfPurchaseDAO {
	private DataSource dataSource ;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<BillOfPurchase> getList() {      // 列出進貨單明細
		String sql = "SELECT * FROM BillOfPurchase";
		return getList(sql);
	}
	
	public void examineGoods(long bopSerial, boolean passed){   //驗貨
		String sql = "UPDATE BillOfPurchase SET passed=?"
				+ "WHERE  BOP_ serial= ? ";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, passed);
			smt.setLong(2,bopSerial);
			smt.executeUpdate();	
			smt.close();

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
	
	public void transferIntoBOP(PurchasingRequisition aPurchaseingRequisition) { 
		   String sql = "INSERT INTO BillOfPurchase (BOP_serial, employee_id, time, has_arrived, total_amount, accountant_id, has_paid, passed) VALUES(?,null,now(),false,?, null, false,false)";	
		   String sql2 = "INSERT INTO Product_connect_BOP(product_id, BOP_serial, price, quantity) VALUES(?, ?, ?, ?)";
		   try {
				conn = dataSource.getConnection();
				smt = conn.prepareStatement(sql);
				smt.setLong(1, aPurchaseingRequisition.getPrSerial());
				smt.setInt(2, aPurchaseingRequisition.getRequisitionContent().getListPrice());
				smt.executeUpdate();		
				smt.close();
				
				// second SQL command
				PurchaseOrder orderContent = aPurchaseingRequisition.getRequisitionContent();
				for(int i = 0; i < orderContent.getList().size(); i++) {
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
					} catch (SQLException e) {}
				}
			}
	   	
	   }
	
   public ArrayList<BillOfPurchase> getList(String sql) {		
		ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
		//will need this part later
		
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
				BillOfPurchase aBillOfPurchase = new BillOfPurchase(0, 0, null, false, 0, 0, false, false);
				aBillOfPurchase.setBopSerial(rs.getInt("BOP_serial"));			
				aBillOfPurchase.setEmployeeID(rs.getLong("employee_id"));
				aBillOfPurchase.setDateTime(rs.getDate("time"));
				aBillOfPurchase.setHasArrived(rs.getBoolean("has_arrived"));
				aBillOfPurchase.setTotalAmount(rs.getInt("total_amount"));			
				aBillOfPurchase.setAccountantId(rs.getInt("accountant_id"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("has_paid"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("passed"));
				billOfPurchaseList.add(aBillOfPurchase);

			}
			rs.close();
			smt.close();
			
			String sql2 = "SELECT * FROM Product_connect_BOP WHERE BOP_serial = ?";
			for(int i = 0; i < billOfPurchaseList.size(); i++) {
				BillOfPurchase currentBill = billOfPurchaseList.get(i);
				smt = conn.prepareStatement(sql2);
				smt.setLong(1, currentBill.getBopSerial());
				rs = smt.executeQuery();
				
				while(rs.next()) {
					// the product name hasn't been set into the object.
					PurchasedProduct aProduct = new PurchasedProduct();
					aProduct.setProductID(rs.getLong("product_id"));
					aProduct.setProductPrice(rs.getInt("price"));
					aProduct.setPurchasingAmount(rs.getInt("quantity"));
					currentBill.getBopContent().add(aProduct);
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
				} catch (SQLException e) {}
			}
		}
		 return billOfPurchaseList;	
	}
   
   

   public ArrayList<BillOfPurchase> showUnpaidProduct() {  //顯示到貨且未付款商品
	 String sql=("SELECT * from BillOfPurchase WHERE has_arrived = ? and has_paid = ?");
	 ArrayList<BillOfPurchase> billOfPurchaseList = new ArrayList<BillOfPurchase>();
	 try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, true);
			smt.setBoolean(2, false);
			rs = smt.executeQuery();
			while(rs.next()){
				BillOfPurchase aBillOfPurchase = new BillOfPurchase(0, 0, null, false, 0, 0, false, false);
				aBillOfPurchase.setBopSerial(rs.getLong("BOP_serial"));			
				aBillOfPurchase.setEmployeeID(rs.getLong("employee_id"));
				aBillOfPurchase.setDateTime(rs.getDate("time"));
				aBillOfPurchase.setHasArrived(rs.getBoolean("has_arrived"));
				aBillOfPurchase.setTotalAmount(rs.getInt("total_amount"));			
				aBillOfPurchase.setAccountantId(rs.getInt("accountant_id"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("has_paid"));
				aBillOfPurchase.setHasPaid(rs.getBoolean("passed"));
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
				} catch (SQLException e) {}
			}
		}
	 return billOfPurchaseList ;
	}	

   public void paid(long bopSerial){  //付款
		String sql = "UPDATE BillOfPurchase SET has_paid=?"
				+ "WHERE  BOP_Serial= ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setBoolean(1, true);
			smt.setLong(2,bopSerial);
			smt.executeUpdate();			
			smt.close();

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
   public ArrayList<Remark> showRemark(long aBopSerial){ //顯示備註
	String sql = "SELECT * FROM Remarks where BOP_Serial = ?";
	ArrayList<Remark> remarks = new ArrayList<Remark>();
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		smt.setLong(1,aBopSerial);
		rs = smt.executeQuery();
		while(rs.next()){
			Remark aremark = new Remark(0, "", 0);
			aremark.setBopSerial(rs.getLong("BopSerial"));			
			aremark.setRemark(rs.getString("remark"));
			aremark.setRemarkIndex(rs.getInt("remark_times"));
			aremark.setRemarkDate(rs.getDate("remark_time"));
			remarks.add(aremark);
		}
		smt.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);

	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	return remarks;
   }

public void addRemark(long aBopSerial,Remark remark){ // 新增備註
	
	 String sql = "INSERT INTO Remarks (Bop_Serial, remark_times, remark, remark_time) VALUES(?, ?, ?, ?)";	
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			//Remark aRemark = new Remark(aBopSerial, "", 0);
			smt.setLong(1, aBopSerial);
			smt.setInt(2, remark.getRemarkIndex());
			smt.setString(3, remark.getRemark());
			smt.setDate(4, (Date) remark.getRemarkDate());
			smt.executeUpdate();			
			smt.close();

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
public void deleteRemark(long aBopSerial, Remark remark) { //刪除備註
	
	String sql = "DELETE FROM Remarks WHERE BOP_Serial = ? AND remark_times = ?";
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		smt.setLong(1, aBopSerial);
		smt.setInt(2,remark.getRemarkIndex());
		smt.executeUpdate();			
		smt.close();

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
public void updateRemark(long aBopSerial,Remark remark ) {  //修改備註
	
	String sql = "UPDATE Remarks SET remark=?, remark_time=? "
			+ "WHERE  BOP_Serial = ? AND remark_times = ?";
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		smt.setString(1, remark.getRemark());
		smt.setDate(2,(Date) remark.getRemarkDate());
		smt.setLong(3,aBopSerial);
		smt.setInt(4,remark.getRemarkIndex());
		smt.executeUpdate();			
		smt.close();

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

//public List<Remark> getListRemark(String sql) {
//	
//	List<Remark> remark = new ArrayList<Remark>();
//	//will need this part later
//	
//	try {
//		conn = dataSource.getConnection();
//		smt = conn.prepareStatement(sql);
//		rs = smt.executeQuery();
//		while(rs.next()){
//			Remark aremark = new Remark(0, "", 0);
//			aremark.setBopSerial(rs.getInt("Bop_Serial"));			
//			aremark.setRemark(rs.getString("remark"));
//			aremark.setRemarkIndex(rs.getInt("remark_times"));
//			aremark.setRemarkDate(rs.getDate("remark_time"));
//
//		}
//		rs.close();
//		smt.close();
//
//	} catch (SQLException e) {
//		throw new RuntimeException(e);
//
//	} finally {
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {}
//		}
//	}
//	 return remark;	
//}
//  
}
