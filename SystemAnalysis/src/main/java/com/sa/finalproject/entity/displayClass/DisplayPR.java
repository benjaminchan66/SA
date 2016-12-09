package com.sa.finalproject.entity.displayClass;

import java.sql.Date;

import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;

public class DisplayPR {
	long procurementID;
	String procurementName;
	PurchasingRequisition pr;
	
	
	public DisplayPR() {
		this(0, "", new PurchasingRequisition());
	}
	
	public DisplayPR(long procurementID, String procurementName, PurchasingRequisition pr) {
		super();
		this.procurementID = procurementID;
		this.procurementName = procurementName;
		this.pr = pr;
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

	public PurchasingRequisition getPr() {
		return pr;
	}

	public void setPr(PurchasingRequisition pr) {
		this.pr = pr;
	}
	
	public long getPrSerial() {
		return pr.getPrSerial();
	}

	public long getEmployeeID() {
		return pr.getEmployeeID();
	}

	

	public Date getDate() {
		return pr.getDate();
	}

	

	public boolean isConfirmed() {
		return pr.isConfirmed();
	}

	

	public Date getConfirmingDate() {
		return pr.getConfirmingDate();
	}

	

	public long getJudgementManagerID() {
		return pr.getJudgementManagerID();
	}

	

	public PurchaseOrder getRequisitionContent() {
		return pr.getRequisitionContent();
	}

	public Supplier getSupplier() {
		return pr.getSupplier();
	}

	
}