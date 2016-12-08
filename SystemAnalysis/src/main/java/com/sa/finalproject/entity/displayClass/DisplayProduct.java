package com.sa.finalproject.entity.displayClass;

import com.sa.finalproject.entity.Product;
import com.sa.finalproject.entity.Supplier;

public class DisplayProduct {
	Product product;
	Supplier supplier;
	
	public DisplayProduct() {
		this.product = new Product();
		this.supplier = new Supplier();
	}
	
	
	public DisplayProduct(Product product, Supplier supplier) {
		super();
		this.product = product;
		this.supplier = supplier;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public long getProductID() {
		return product.getProductID();
	}

	public String getProductName() {
		return product.getProductName();
	}

	public int getPrice() {
		return product.getPrice();
	}

	public long getSupplierID() {
		return supplier.getSupplierID();
	}


	public int getInventory() {
		return product.getInventory();
	}

	public boolean isInTheMarket() {
		return product.isInTheMarket();
	}
	
	
}