package com.sa.finalproject.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//configuration for session, please refer to: http://tuhrig.de/making-a-spring-bean-session-scoped/
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)

// super class of all the staff
public class Employee {
	private String id ;
	private String name ;
	private String phone;
	private String address;
	private String level;
	private String dep;
	
	public Employee() {
		this("", "", "", "", "", "");
	}
	
	public Employee(String id, String name, String phone, String address, String level, String dep) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.level = level;
		this.dep = dep;
	}


	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
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

}
