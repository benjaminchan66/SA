package com.sa.finalproject.entity.displayClass;

import java.util.Date;

import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;

public class DisplayBOP {
	long procurementID;
	String procurementName;
	BillOfPurchase bop;
	String supplierName;
	
	
	public DisplayBOP() {
		this(0, "", new BillOfPurchase(), "");
	}
	
	
	public DisplayBOP(long procurementID, String procurementName, BillOfPurchase bop, String supplierName) {
		super();
		this.procurementID = procurementID;
		this.procurementName = procurementName;
		this.bop = bop;
	}


	public long getProcurementID() {
		return procurementID;
	}


	public void setProcurementID(long procurementID) {
		this.procurementID = procurementID;
	}


	public String getProcurementName() {
		return procurementName;
	}


	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}


	public BillOfPurchase getBop() {
		return bop;
	}


	public void setBop(BillOfPurchase bop) {
		this.bop = bop;
	}
	
	public long getBopSerial() {
		return bop.getBopSerial();
	}


	public long getEmployeeID() {
		return bop.getEmployeeID();
	}

	public Date getDateTime() {
		return bop.getDateTime();
	}


	public boolean isHasArrived() {
		return bop.isHasArrived();
	}

	public int getTotalAmount() {
		return bop.getTotalAmount();
	}

	public long getAccountantId() {
		return bop.getAccountantId();
	}

	public boolean isHasPaid() {
		return bop.isHasPaid();
	}

	public boolean isPassed() {
		return bop.isPassed();
	}

	

	public String getRemarks() {
		return bop.getRemarks();
	}

	public PurchaseOrder getBopContent() {
		return bop.getBopContent();
	}

	public long getSupplierID() {
		return bop.getSupplierID();
	}
	
	
}