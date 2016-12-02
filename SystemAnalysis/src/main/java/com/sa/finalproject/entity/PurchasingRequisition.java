package com.sa.finalproject.entity;

import java.sql.Date;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.Supplier;

public class PurchasingRequisition {
	private long prSerial = 0;
	
	// employeeID 表示開立請購單的員工
	private long employeeID = 0;
	private Date date = null ;
	private boolean isConfirmed = false;
	private Supplier supplier = null;
	
	// 非A級廠商需要確認，此為確認時間
	private Date confirmingDate = null;
	private long judgementManagerID = 0 ;
	private PurchaseOrder requisitionContent = null;
	
	
	// initialize
	public PurchasingRequisition() {
		this(0, null);
	}
	
	
	
	public PurchasingRequisition(long employeeID, Date date) {
		super();
		this.employeeID = employeeID;
		this.date = date;
	}
	
	public PurchasingRequisition(long prSerial, long employeeID, Date date, boolean isConfirmed, Date confirmingDate, long judgementManagerID, PurchaseOrder requisitionContent) {
		super();
		this.prSerial = prSerial;
		this.employeeID = employeeID;
		this.date = date;
		this.isConfirmed = isConfirmed;
		this.confirmingDate = confirmingDate;
		this.judgementManagerID = judgementManagerID;
		this.requisitionContent = requisitionContent;
	}

	// getter and setter
	public long getPrSerial() {
		return prSerial;
	}

	public void setPrSerial(long prSerial) {
		this.prSerial = prSerial;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Date getConfirmingDate() {
		return confirmingDate;
	}

	public void setConfirmingDate(Date confirmingDate) {
		this.confirmingDate = confirmingDate;
	}

	public long getJudgementManagerID() {
		return judgementManagerID;
	}

	public void setJudgementManagerID(long judgementManagerID) {
		this.judgementManagerID = judgementManagerID;
	}

	public PurchaseOrder getRequisitionContent() {
		return requisitionContent;
	}

	public void setRequisitionContent(PurchaseOrder requisitionContent) {
		this.requisitionContent = requisitionContent;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}