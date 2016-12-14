package com.sa.finalproject.entity.displayClass;

import java.util.Date;

import com.sa.finalproject.entity.Employee;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.WarehouseWarrant;

public class DisplayWW extends WarehouseWarrant {
	Employee employee = new Employee();
	Supplier wwSupplier = new Supplier();
	
	
	public DisplayWW() {
		super();
	}

	public DisplayWW(Employee employee, Supplier wwSupplier) {
		super();
		this.employee = employee;
		this.wwSupplier = wwSupplier;
	}
	
	public void setEmployee(Employee staff) {
		this.employee.setId(staff.getId());
		this.employee.setName(staff.getName());
		this.employee.setLevel(staff.getLevel());
		this.employee.setAddress(staff.getAddress());
		this.employee.setDep(staff.getDep());
	}
	
	public void setSupplier(Supplier supplier) {
		wwSupplier.setSupplierID(supplier.getSupplierID());
		wwSupplier.setSupplierName(supplier.getSupplierName());
		wwSupplier.setPhone(supplier.getPhone());
		wwSupplier.setAddress(supplier.getAddress());
		wwSupplier.setLevel(supplier.getAddress());
	}
	
	public void setWW(WarehouseWarrant ww) {
		this.setWwSerial(ww.getWwSerial());
		this.setSupplierID(ww.getSupplierID());
		this.setDate(ww.getDate());
		this.setEmployeeID(ww.getEmployeeID());
		this.setWwContent(ww.getWwContent());
	}
	
	public long getWwSerial() {
		return super.getWwSerial();
	}
	public void setWwSerial(long wwSerial) {
		super.setWwSerial(wwSerial);
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
	public long getSupplierID() {
		return super.getSupplierID();
	}
	public void setSupplierID(long supplierID) {
		super.setSupplierID(supplierID);
	}
	public PurchaseOrder getWwContent() {
		return super.getWwContent();
	}
	public void setWwContent(PurchaseOrder wwContent) {
		super.setWwContent(wwContent);
	}
	
	public String getSupplierName() {
		return this.wwSupplier.getSupplierName();
	}
	
	public void setEmployeeName(String name) {
		this.employee.setName(name);
	}
	
	public String getEmployeeName() {
		return this.employee.getName();
	}
	
	
}
