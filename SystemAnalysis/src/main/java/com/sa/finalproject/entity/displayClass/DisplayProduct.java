package com.sa.finalproject.entity.displayClass;

import com.sa.finalproject.entity.Product;
import com.sa.finalproject.entity.Supplier;

public class DisplayProduct extends Product {
	String supplierName = "";

	public DisplayProduct() {
		super();
		this.supplierName = "";
	}

	public DisplayProduct(String supplierName) {
		super();
		this.supplierName = supplierName;
	}

	public DisplayProduct(Product aProduct, String supplierName) {
		super(aProduct.getProductID(), aProduct.getProductName(), aProduct.getPrice(), aProduct.getInventory(),
				aProduct.isInTheMarket(), aProduct.getSupplierID());
		this.supplierName = supplierName;
	}

	public DisplayProduct(long productID, String productName, int price, int inventory, boolean isInTheMarket,
			long supplierID, String supplierName) {
		super(productID, productName, price, inventory, isInTheMarket, supplierID);
		this.supplierName = supplierName;
	}

	// getter and setter
	public void setProduct(Product aProduct) {
		setProductID(aProduct.getProductID());
		setProductName(aProduct.getProductName());
		setPrice(aProduct.getPrice());
		setInventory(aProduct.getInventory());
		setInTheMarket(aProduct.isInTheMarket());
		setSupplierID(aProduct.getSupplierID());
	}
	
	public long getProductID() {
		return super.getProductID();
	}

	public void setProductID(long productID) {
		super.setProductID(productID);
	}

	public String getProductName() {
		return super.getProductName();
	}

	public void setProductName(String productName) {
		super.setProductName(productName);
	}

	public int getPrice() {
		return super.getPrice();
	}

	public void setPrice(int price) {
		super.setPrice(price);
	}

	public long getSupplierID() {
		return super.getSupplierID();
	}

	public void setSupplierID(long supplierID) {
		super.setSupplierID(supplierID);
	}

	public int getInventory() {
		return super.getInventory();
	}

	public void setInventory(int inventory) {
		super.setInventory(inventory);
	}

	public boolean isInTheMarket() {
		return super.isInTheMarket();
	}

	public void setInTheMarket(boolean isInTheMarket) {
		super.setInTheMarket(isInTheMarket);
	}
	
	public String getProductStatus() {
		if(this.isInTheMarket())
			return "供貨中";
		else
			return "斷貨";
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}