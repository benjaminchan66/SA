package com.sa.finalproject.DAO;

import com.sa.finalproject.entity.BillOfPurchase;
import com.sa.finalproject.entity.PurchasingRequisition;
import com.sa.finalproject.entity.WarehouseWarrant;
import java.util.List;

public interface WarehouseWarrantDAO {
	// 新增入庫單
	public void insert(PurchasingRequisition pr, long employeeID);
	// 列出所有入庫單
	public List<WarehouseWarrant> getList();
	// 取得特定入庫單
	public WarehouseWarrant get(long WW_serial);
	
}