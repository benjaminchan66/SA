package com.sa.finalproject.entity;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import com.sa.finalproject.entity.supportingClass.PurchasedProduct;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)

public class PurchaseOrder {
	// 存放準備進行採購的資料，網頁上的list 不需區分供應商
	private ArrayList<PurchasedProduct> cartList = new ArrayList<PurchasedProduct>();
	private long supplierID = 0;
	
	public PurchaseOrder() {
	}
	
	public PurchaseOrder(long supplierID) {
		this.supplierID = supplierID;
	}
	
	
	// 採購清單功能
	public ArrayList<PurchasedProduct> getList() {
		return this.cartList;
	}
	
	public void add(PurchasedProduct aProduct) {
		this.cartList.add(aProduct);
	}
	
	public void cleanTheList() {
		this.cartList.clear();
	}
	
	public int count() {
		// 回傳產品種類的數量
		return this.cartList.size();
	}
	
	// return true if the product has been removed from the list.
	public boolean drop(int aProductIndex) {
		if(aProductIndex < this.cartList.size()) {
			this.cartList.remove(aProductIndex);
			return true;
		}else {
			return false;
		}
	}
	
	// 為特定產品的數量加一 
	public int addOne(int index) {
		PurchasedProduct product = this.cartList.get(index);
		int newAmount = product.addOne();
		
		return newAmount;
	}
	
	// 為特定產品的數量減一
	public int minusOne(int index) {
		PurchasedProduct product = this.cartList.get(index);
		int newAmount = product.minusOne();
		
		if(newAmount == 0) {
			// 如果訂購數量為0則自動清除該項產品ß
			this.drop(index);
		}
		
		return newAmount;
	}
	
	// 取得這筆交易之總金額
	public int getListPrice() {
		int listPrice = 0;
		for(int i = 0; i < this.cartList.size(); i++) {
			PurchasedProduct product = this.cartList.get(i);
			listPrice += product.getTotalPrice();
		}
		
		return listPrice;
	}


	public long getSupplierID() {
		return supplierID;
	}


	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}
}