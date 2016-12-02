package com.sa.finalproject.entity.supportingClass;


// when the order hasn't been sent, each product in order will be stored in this class
public class PurchasedProduct {
	private long productID;
	private String productName;
	private int productPrice;
	private int purchasingAmount;
	private long supplierID;
	private String supplierName;
	
	public PurchasedProduct() {
		this(0, "", 1, 0, "");
	}

	public PurchasedProduct(long productID, String productName, int purchasingAmount, long supplierID, String supplierName) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.purchasingAmount = purchasingAmount;
		this.supplierID = supplierID;
		this.supplierName = supplierName;
	}
	
	public int addOne() {
		this.purchasingAmount++;
		return this.purchasingAmount;
	}
	
	public int minusOne() {
		if(this.purchasingAmount >= 1) {
			this.purchasingAmount--;
		}
		return this.purchasingAmount;
	}

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

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getPurchasingAmount() {
		return purchasingAmount;
	}

	public void setPurchasingAmount(int purchasingAmount) {
		this.purchasingAmount = purchasingAmount;
	}

	public long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public int getTotalPrice() {
		return this.purchasingAmount * this.productPrice;
	}
	
}