package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.sa.finalproject.DAO.RemarkDAO;
import com.sa.finalproject.entity.Remark;

public class RemarkDAOImpl implements RemarkDAO {
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<Remark> showRemark(long aBopSerial){ //顯示備註
		String sql = "SELECT * FROM Remarks where BOP_serial = ?";
		ArrayList<Remark> remarks = new ArrayList<Remark>();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1,aBopSerial);
			rs = smt.executeQuery();
			while(rs.next()){
				Remark aremark = new Remark(0, "", 0);
				aremark.setBopSerial(rs.getLong("BOP_serial"));			
				aremark.setRemark(rs.getString("remark"));
				aremark.setRemarkIndex(rs.getInt("remark_times"));
				aremark.setRemarkDate(rs.getDate("remark_time"));
				remarks.add(aremark);
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
		return remarks;
	   }

	public void addRemark(long aBopSerial,Remark remark){ // 新增備註
		
		int newRemarkIndex = findLastRemark(aBopSerial);
		
		 String sql = "INSERT INTO Remarks (Bop_Serial, remark_times, remark, remark_time) VALUES(?, ?, ?, ?)";	
			try {
				conn = dataSource.getConnection();
				smt = conn.prepareStatement(sql);
				//Remark aRemark = new Remark(aBopSerial, "", 0);
				smt.setLong(1, aBopSerial);
				smt.setInt(2, newRemarkIndex);
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
	private int findLastRemark(long aBopSerial) {
		// TODO Auto-generated method stub
		
		
		String sql = "SELECT * FROM Reamrks WHERE BOP_serial = ?";
		int numberOfRemarks = 0;
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			
			smt.setLong(1, aBopSerial);
			
			rs = smt.executeQuery();
			
			numberOfRemarks = 0;
			while(rs.next()) {
				numberOfRemarks++;
			}
			
			
			rs.close();
			smt.close();
			
			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				return ++numberOfRemarks;
			} catch(SQLException exc) {}
			
		}
		return 0;
		
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
}
