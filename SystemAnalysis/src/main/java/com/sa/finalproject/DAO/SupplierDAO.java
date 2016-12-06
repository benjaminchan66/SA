package com.sa.finalproject.DAO;

import com.sa.finalproject.entity.Supplier;

import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO {
	//新增廠商
	public long insert(Supplier aSupplier);
	// 列出廠商清單
	public ArrayList<Supplier> getList();
	// 取得特定廠商資料
	public Supplier get(long supplierID);
	// 變更廠商級數
	public void changeSupplierLevel(long supplierID, String newLevel);
	//列出有Remarks的廠商
	public ArrayList<Supplier> remarklist();
}