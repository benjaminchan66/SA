package com.sa.finalproject.entity;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//configuration for session, please refer to: http://tuhrig.de/making-a-spring-bean-session-scoped/
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)

public class WarehouseWarrant {
	private long wwSerial = 0;
	// employeeID means warehouse staff's ID
	private long employeeID = 0;
	private Date date = new Date();
	private long supplierID = 0;
	
	//入庫單內容物
	private PurchaseOrder wwContent = null;
	
	public WarehouseWarrant() {
		
	}
	
	public WarehouseWarrant(long wwSerial, long employeeID, Date date, long supplierID) {
		super();
		this.wwSerial = wwSerial;
		this.employeeID = employeeID;
		this.date = date;
		this.supplierID = supplierID;
	}
	
	
	public WarehouseWarrant(long wwSerial, long employeeID, Date date, long supplierID, PurchaseOrder wwContent) {
		super();
		this.wwSerial = wwSerial;
		this.employeeID = employeeID;
		this.date = date;
		this.supplierID = supplierID;
		this.wwContent = wwContent;
	}

	public long getWwSerial() {
		return wwSerial;
	}
	public void setWwSerial(long wwSerial) {
		this.wwSerial = wwSerial;
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
	public long getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}
	public PurchaseOrder getWwContent() {
		return wwContent;
	}
	public void setWwContent(PurchaseOrder wwContent) {
		this.wwContent = wwContent;
	}
	
	
	
}