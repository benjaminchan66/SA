package com.sa.finalproject.DAO;

import java.util.ArrayList;

import com.sa.finalproject.entity.Product;


public interface ProductDAO {
	
	// CRUD 
	public long insert(Product aProduct);
	// 產品下架，不刪除產品，而是將 isInTheMarket 轉為 false
	public void takeOff(long aProductID);
	public void update(long aProductID, Product newProductInfo);
	
	// 回傳所有產品(包含已不再進貨之產品)的列表，依照廠商分類
	public ArrayList<Product> getList();
	public ArrayList<Product> getAvailableList();
	// 取得特定產品的詳細資料
	public Product get(long aProductID);
	
	// 產品上架
	public void hitTheShelf(long aProductID);
	
	
	

}