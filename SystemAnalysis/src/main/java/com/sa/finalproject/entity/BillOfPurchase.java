package com.sa.finalproject.entity;

import java.util.Date;
import java.util.ArrayList;

public class BillOfPurchase {
	private long bopSerial = 0;
	// employeeID 表示驗貨人員
	private long employeeID = 0;
	private Date dateTime = new Date();
	private long supplierID = 0;
	/* Delivery status means whether the goods have arrived, 
	 in this class we used hasArrived instead of delivery status */
	private boolean hasArrived = false;
	private int totalAmount = 0;
	private long accountantId = 0;
	private boolean hasPaid = false;
	private boolean passed = false;
	
	// remarks
	private String remarks;
	
	// bill of purchase content
	private PurchaseOrder bopContent = new PurchaseOrder();
	
	public BillOfPurchase() {
	}
	
	public BillOfPurchase(long bopSerial, long employeeID, Date dateTime, boolean hasArrived, int totalAmount, long accountantId, boolean hasPaid, boolean passed, String remarks) {
		super();
		this.bopSerial = bopSerial;
		this.employeeID = employeeID;
		this.dateTime = dateTime;
		this.hasArrived = hasArrived;
		this.totalAmount = totalAmount;
		this.accountantId = accountantId;
		this.hasPaid = hasPaid;
		this.passed = passed;
		this.remarks = remarks;
	}
	
	public long getBopSerial() {
		return bopSerial;
	}


	public void setBopSerial(long bopSerial) {
		this.bopSerial = bopSerial;
	}


	public long getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}


	public Date getDateTime() {
		return dateTime;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public boolean isHasArrived() {
		return hasArrived;
	}

	public void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}


	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getAccountantId() {
		return accountantId;
	}

	public void setAccountantId(long accountantId) {
		this.accountantId = accountantId;
	}

	public boolean isHasPaid() {
		return hasPaid;
	}

	public void setHasPaid(boolean hasPaid) {
		this.hasPaid = hasPaid;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public void setRemarks(String newRemarks) {
		this.remarks = newRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public PurchaseOrder getBopContent() {
		return bopContent;
	}

	public void setBopContent(PurchaseOrder bopContent) {
		this.bopContent = bopContent;
	}

	public long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}
	
}