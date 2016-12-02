package com.sa.finalproject.entity;


// 用於顯示庫存等情況
public class Product {
	private long productID ;
	private String productName ;
	private int price ;
	private int inventory ;
	private boolean isInTheMarket;
	private long supplierID ;
	
	
	// initialize
	public Product() {
		this(0, "", 0, 0, true, 0);
	}
	
	public Product(String productName, int price, long supplierID) {
		this(0, productName, price, 0, true, supplierID);
	}
	
	public Product(long productID, String productName, int price, int inventory, boolean isInTheMarket, long supplierID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.inventory = inventory;
		this.isInTheMarket = isInTheMarket;
		this.supplierID = supplierID;
	}




	// getter and setter
	public long getProductID() {
		return productID;
	}


	public void setProductID(long productID) {
		this.productID = productID;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public long getSupplierID() {
		return supplierID;
	}


	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}


	public int getInventory() {
		return inventory;
	}


	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public boolean isInTheMarket() {
		return isInTheMarket;
	}

	public void setInTheMarket(boolean isInTheMarket) {
		this.isInTheMarket = isInTheMarket;
	}
	
}
