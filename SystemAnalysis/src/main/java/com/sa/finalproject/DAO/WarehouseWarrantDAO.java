package com.sa.finalproject.DAO;

import com.sa.finalproject.entity.WarehouseWarrant;
import java.util.List;

public interface WarehouseWarrantDAO {
	public void insert(WarehouseWarrant aWarehouseWarrant);
	public List<WarehouseWarrant> getList();
	public WarehouseWarrant get(WarehouseWarrant aWarehouseWarrant);
	
}