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
	// bopEmployeeID means inspector's ID
	private long bopEmployeeID = 0;
	private Date date = new Date();
	private long supplierID = 0;
	
	
	public WarehouseWarrant(long wwSerial, long employeeID, long bopEmployeeID, Date date, long supplierID) {
		super();
		this.wwSerial = wwSerial;
		this.employeeID = employeeID;
		this.bopEmployeeID = bopEmployeeID;
		this.date = date;
		this.supplierID = supplierID;
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
	public long getBopEmployeeID() {
		return bopEmployeeID;
	}
	public void setBopEmployeeID(long bopEmployeeID) {
		this.bopEmployeeID = bopEmployeeID;
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
	
	
	
}