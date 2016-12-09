package com.sa.finalproject.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.sa.finalproject.DAO.ProductDAO;
import com.sa.finalproject.entity.Product;

public class ProductDAOImpl implements ProductDAO {
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public long insert(Product aProduct) {
		// SQL command
		
		
		String sql = "INSERT INTO Product (product_name, product_price, inventory, isInTheMarket, supplier_id) VALUES(?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO SuppliedProduct(supplier_id, product_id) VALUES(?, ?)";
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			smt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//configure the SQL command
			smt.setString(1, aProduct.getProductName());
			smt.setInt(2, aProduct.getPrice());
			smt.setInt(3, aProduct.getInventory());
			smt.setBoolean(4, true);
			smt.setLong(5, aProduct.getSupplierID());
			
			
			//execute the SQL command
			smt.executeUpdate();
			
			ResultSet productRs = smt.getGeneratedKeys();
			if(productRs.next()) {
				long newProductID = productRs.getLong(1);
				aProduct.setProductID(newProductID);
			}
			
			smt.close();
			
			smt = conn.prepareStatement(sql2);
			
			smt.setLong(1, aProduct.getSupplierID());
			smt.setLong(2, aProduct.getProductID());
			
			smt.executeUpdate();
			smt.close();
			
			conn.commit();
			conn.setAutoCommit(true);
			
			return aProduct.getProductID();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				// do nothing
			}
		}
		
	}
	@Override
	public void takeOff(long aProductID) {
		// 產品下架
		
		String sql = "UPDATE Product SET isInTheMarket = false WHERE product_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			
			smt.setLong(1, aProductID);
			
			smt.executeUpdate();
			smt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e){}
		}
		
	}

	@Override
	public void update(long aProductID, Product newProductInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Product SET product_name = ?, product_price = ?, inventory = ?, isInTheMarket = ?, supplier_id = ? WHERE product_id = ?";
		String sql2 = "UPDATE SuppliedProduct SET supplier_id = ? WHERE product_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			
			smt.setString(1, newProductInfo.getProductName());
			smt.setInt(2, newProductInfo.getPrice());
			smt.setInt(3, newProductInfo.getInventory());
			smt.setBoolean(4, newProductInfo.isInTheMarket());
			smt.setLong(5, newProductInfo.getSupplierID());
			
			smt.executeUpdate();
			smt.close();
			
			smt = conn.prepareStatement(sql2);
			smt.setLong(1, newProductInfo.getSupplierID());
			smt.setLong(2, aProductID);
			smt.executeUpdate();
			smt.close();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e){}
		}
	}

	@Override
	public ArrayList<Product> getList() {
		// TODO Auto-generated method stub
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String sql = "SELECT * FROM Product";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			
			rs = smt.executeQuery();
			
			while(rs.next()) {
				long productID = rs.getLong("product_id");
				String productName = rs.getString("product_name");
				int price = rs.getInt("product_price");
				int inventory = rs.getInt("inventory");
				boolean isInTheMarket = rs.getBoolean("isInTheMarket");
				long supplierID = rs.getLong("supplier_id");
				
				productList.add(new Product(productID, productName, price, inventory, isInTheMarket, supplierID));
				
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
			} catch (SQLException e){}
		}
		
		return productList;
	}

	@Override
	public ArrayList<Product> getAvailableList() {
		// TODO Auto-generated method stub
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String sql = "SELECT * FROM Product WHERE isInTheMarket = true";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			
			this.rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				long productID = rs.getLong("product_id");
				String productName = rs.getString("product_name");
				int price = rs.getInt("product_price");
				int inventory = rs.getInt("inventory");
				boolean isInTheMarket = rs.getBoolean("isInTheMarket");
				long supplierID = rs.getLong("supplier_id");
				
				productList.add(new Product(productID, productName, price, inventory, isInTheMarket, supplierID));
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
			} catch (SQLException e){}
		}
		
		
		return productList;
	}

	@Override
	public Product get(long aProductID) {
		// TODO Auto-generated method stub
		
		Product product = new Product();
		
		String sql = "SELECT * FROM Product WHERE product_id = ?";
		
		try {
			this.conn = this.dataSource.getConnection();
			this.smt = this.conn.prepareStatement(sql);
			
			smt.setLong(1, aProductID);
			
			this.rs = smt.executeQuery();
			
			if(rs.next()) {
				product.setProductID(rs.getLong("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("product_price"));
				product.setInventory(rs.getInt("inventory"));
				product.setInTheMarket(rs.getBoolean("isInTheMarket"));
				product.setSupplierID(rs.getLong("supplier_id"));
			}
			rs.close();
			smt.close();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e){}
		}
		
		return product;
	}
	
	
	@Override
	public void hitTheShelf(long aProductID) {
		String sql = "SELECT * FROM Product WHERE product_id = ?";
		
		try {
			this.conn = this.dataSource.getConnection();
			this.smt = this.conn.prepareStatement(sql);
			
			smt.setLong(1, aProductID);
			
			smt.executeUpdate();
			smt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e){}
		}
	}
	
	public ArrayList<Product> getProductOf(long aSupplierID) {
		ArrayList<Product> productList = new ArrayList<Product>();
		String sql = "SELECT * FROM Product WHERE supplier_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, aSupplierID);
			
			ArrayList<Long> idList = new ArrayList<Long>();
			rs = smt.executeQuery();
			while(rs.next()) {
				idList.add(rs.getLong("product_id"));
			}
			rs.close();
			smt.close();
			
			for(int i = 0; i < idList.size(); i++) {
				Product currentProduct = this.get(idList.get(i));
				productList.add(currentProduct);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e){}
		}
		
		
		return productList;
	}
	
}