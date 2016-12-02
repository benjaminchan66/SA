package com.sa.finalproject.entity;

public class Supplier {
	private long id = 0;
	private String name = "";
	private String phone = "";
	private String address = "";
	private String level = "A";
	
	
	public Supplier() {
	}
	
	public Supplier(long id, String name, String phone, String address, String level) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.level = level;
	}


	public long getSupplierID() {
		return id;
	}


	public void setSupplierID(long supplierID) {
		this.id = supplierID;
	}


	public String getSupplierName() {
		return name;
	}


	public void setSupplierName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
