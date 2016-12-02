package com.sa.finalproject.entity;

import java.util.Date;

public class Remark {
	private long bopSerial = 0;
	private String remark = "";
	// remarkIndex 記錄這是這張進貨單的第幾項備註
	private int remarkIndex = 0;
	private Date remarkDate = new Date();
	
	public Remark(long bopSerial, String remark, int remarkIndex) {
		super();
		this.bopSerial = bopSerial;
		this.remark = remark;
		this.remarkIndex = remarkIndex;
		this.remarkDate = new Date();
	}
	
	public Remark(long bopSerial, String remark, int remarkIndex, Date remarkDate) {
		super();
		this.bopSerial = bopSerial;
		this.remark = remark;
		this.remarkIndex = remarkIndex;
		this.remarkDate = remarkDate;
	}


	public long getBopSerial() {
		return bopSerial;
	}


	public void setBopSerial(long bopSerial) {
		this.bopSerial = bopSerial;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRemarkIndex() {
		return remarkIndex;
	}


	public void setRemarkIndex(int remarkIndex) {
		this.remarkIndex = remarkIndex;
	}


	public Date getRemarkDate() {
		return remarkDate;
	}


	public void setRemarkDate(Date remarkDate) {
		this.remarkDate = remarkDate;
	}
	
	
}