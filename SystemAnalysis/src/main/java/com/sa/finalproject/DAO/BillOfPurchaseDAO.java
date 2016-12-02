package com.sa.finalproject.DAO;

import java.util.ArrayList;
import java.util.List;
import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchasingRequisition;




public interface BillOfPurchaseDAO{
	public ArrayList<BillOfPurchase> getList(); //列出進貨單明細
	public void examineGoods(long aBopSerial,boolean passed); //驗貨
	public void transferIntoBOP(PurchasingRequisition aPurchaseingRequisition); //將請購單轉為進貨單
	public ArrayList<BillOfPurchase> showUnpaidProduct(); //顯示到貨且未付款商品
	public void paid(long aBopSerial); //付款
	
	
}
