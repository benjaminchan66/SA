package com.sa.finalproject.DAO;

import java.util.ArrayList;
import java.util.List;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchaseOrder;
import com.sa.finalproject.entity.PurchasingRequisition;




public interface BillOfPurchaseDAO{
	public ArrayList<BillOfPurchase> getList(); //列出進貨單明細
	public BillOfPurchase get(long bopserial); 
	public void examineGoods(long aBopSerial,boolean passed); //驗貨
	public void transferIntoBOP(PurchasingRequisition aPurchaseingRequisition); //將請購單轉為進貨單
	public ArrayList<BillOfPurchase> showUnpaidProduct(); //顯示到貨且未付款商品
	public void paid(long aBopSerial); //付款
	public void update(long bopserial,BillOfPurchase aBillOfPurchase);//修改資料
	
	// 取得單一進貨單之內容物
	public PurchaseOrder getContentOf(long bopSerial);
	
	// 取得有備註的進貨單
	public ArrayList<BillOfPurchase> getRemarkedList();
	

	
	
}
