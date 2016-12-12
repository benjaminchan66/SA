package com.sa.finalproject.entity.displayClass;

import java.util.Date;

import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.Supplier;

public class DisplayBOP extends BillOfPurchase {
	long procurementID;
	String procurementName;
	Supplier supplier = new Supplier();
	
	
	public DisplayBOP() {
		super();
		this.procurementID = 0;
		this.procurementName = "";
		this.supplier = new Supplier();
	}
	
	
	public DisplayBOP(long procurementID, String procurementName) {
		super();
		this.procurementID = procurementID;
		this.procurementName = procurementName;
		this.supplier = new Supplier();
	}
	
	public void setBOP(BillOfPurchase bop) {
		this.setBopSerial(bop.getBopSerial());
		this.setEmployeeID(bop.getEmployeeID());
		this.setDateTime(bop.getDateTime());
		this.setHasArrived(bop.isHasArrived());
		this.setTotalAmount(bop.getTotalAmount());
		this.setHasPaid(bop.isHasPaid());
		this.setPassed(bop.isPassed());
		this.setRemarks(bop.getRemarks());
		this.setBopContent(bop.getBopContent());
	}
	
	public void setSupplier(Supplier supllier) {
		this.supplier = supplier;
	}
	
	public long getBopSerial() {
		return super.getBopSerial();
	}


	public void setBopSerial(long bopSerial) {
		super.setBopSerial(bopSerial);
	}


	public long getEmployeeID() {
		return super.getEmployeeID();
	}


	public void setEmployeeID(long employeeID) {
		super.setEmployeeID(employeeID);
	}


	public Date getDateTime() {
		return super.getDateTime();
	}


	public void setDateTime(Date dateTime) {
		super.setDateTime(dateTime);
	}


	public boolean isHasArrived() {
		return super.isHasArrived();
	}

	public void setHasArrived(boolean hasArrived) {
		super.setHasArrived(hasArrived);
	}


	public int getTotalAmount() {
		return super.getTotalAmount();
	}

	public void setTotalAmount(int totalAmount) {
		super.setTotalAmount(totalAmount);
	}

	public long getAccountantId() {
		return super.getAccountantId();
	}

	public void setAccountantId(long accountantId) {
		super.setAccountantId(accountantId);
	}

	public boolean isHasPaid() {
		return super.isHasPaid();
	}

	public void setHasPaid(boolean hasPaid) {
		super.setHasPaid(hasPaid);
	}

	public boolean isPassed() {
		return super.isPassed();
	}

	public void setPassed(boolean passed) {
		super.setHasPaid(passed);
	}

	public void setRemarks(String newRemarks) {
		super.setRemarks(newRemarks);
	}

	public String getRemarks() {
		return super.getRemarks();
	}

	public PurchaseOrder getBopContent() {
		return super.getBopContent();
	}

	public void setBopContent(PurchaseOrder bopContent) {
		super.setBopContent(bopContent);
	}

	public long getSupplierID() {
		return super.getSupplierID();
	}

	public void setSupplierID(long supplierID) {
		super.setSupplierID(supplierID);
	}
	
	public String getSupplierName() {
		return this.supplier.getSupplierName();
	}
	
	public String deliveryStatus() {
		if(this.isHasArrived()) {
			return "已到貨";
		}else {
			return "未到貨";
		}
	}
	
	public String inspectStatus() {
		if(this.isPassed()) {
			return "已通過";
		}else {
			return "未通過";
		}
	}
	
	public String payingStatus() {
		if(this.isHasPaid()) {
			return "已付款";
		}else {
			return "未付款";
		}
	}
	
	
}