package com.sa.finalproject.entity.displayClass;

import java.sql.Date;

import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;

public class DisplayPR extends PurchasingRequisition {
	// ID, supplier, total amount, getConfirmStatus
	
	int totalAmount = 0;

	public DisplayPR() {
		super();
		this.totalAmount = 0;
	}

	public DisplayPR(int totalAmount) {
		super();
		this.totalAmount = totalAmount;
	}
	
	public void setRequisition(PurchasingRequisition pr) {
		this.setPrSerial(pr.getPrSerial());
		this.setEmployeeID(pr.getEmployeeID());
		this.setDate(pr.getDate());
		this.setConfirmed(pr.isConfirmed());
		this.setConfirmingDate(pr.getConfirmingDate());
		this.setJudgementManagerID(pr.getJudgementManagerID());
		this.setRequisitionContent(pr.getRequisitionContent());
	}

	// getter and setter
	public long getPrSerial() {
		return super.getPrSerial();
	}

	public void setPrSerial(long prSerial) {
		super.setPrSerial(prSerial);
	}

	public long getEmployeeID() {
		return super.getEmployeeID();
	}

	public void setEmployeeID(long employeeID) {
		super.setEmployeeID(employeeID);
	}

	public Date getDate() {
		return super.getDate();
	}

	public void setDate(Date date) {
		super.setDate(date);
	}

	public boolean isConfirmed() {
		return super.isConfirmed();
	}

	public void setConfirmed(boolean isConfirmed) {
		super.setConfirmed(isConfirmed);
	}

	public Date getConfirmingDate() {
		return super.getConfirmingDate();
	}

	public void setConfirmingDate(Date confirmingDate) {
		super.setConfirmingDate(confirmingDate);
	}

	public long getJudgementManagerID() {
		return super.getJudgementManagerID();
	}

	public void setJudgementManagerID(long judgementManagerID) {
		super.setJudgementManagerID(judgementManagerID);
	}

	public PurchaseOrder getRequisitionContent() {
		return super.getRequisitionContent();
	}

	public void setRequisitionContent(PurchaseOrder requisitionContent) {
		super.setRequisitionContent(requisitionContent);
	}

	public Supplier getSupplier() {
		return super.getSupplier();
	}

	public void setSupplier(Supplier supplier) {
		super.setSupplier(supplier);
	}

	public String getSupplierName() {
		return super.getSupplier().getSupplierName();
	}

	public void setSupplierName(Supplier supplier) {
		super.setSupplier(supplier);
	}
	
	public String getConfirmStatus() {
		if(this.isConfirmed()) {
			return "已確認";
		}else {
			return "為確認";
		}
	}

	public int getTotalAmount() {
		this.totalAmount = super.getRequisitionContent().getListPrice();
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
}