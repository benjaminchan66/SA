package com.sa.finalproject.DAO;

import java.util.ArrayList;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.Supplier;
import com.sa.finalproject.entity.PurchaseOrder;

public interface PurchasingRequisitionDAO {
	
	public ArrayList<Long> insert(PurchaseOrder mixedOrder, long employeeID);
	public void confirm(long managerID, long aRequisitionSerial, boolean isConfirmed);
	// 顯示所有請購單
	public ArrayList<PurchasingRequisition> getList();
	// 取得請購單知詳細資料
	public PurchasingRequisition get(long aRequisitionSerial);
	
	// 顯示所有為被確認的請購單（給請購單位主管）
	public ArrayList<PurchasingRequisition> getUnconfirmedOrders();
	
	// 取得該訂單的廠商
	public Supplier getASupplierOf(long prSerial);
	
	// 取得單一請購單的內容物
	public PurchaseOrder getContentOf(long prSerial);
	
	public void confirm(long prSerial, Long managerID);
	
	
}