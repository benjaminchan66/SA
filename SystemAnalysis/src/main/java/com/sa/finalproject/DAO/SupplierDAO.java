package com.sa.finalproject.DAO;

import com.sa.finalproject.entity.Supplier;
import java.util.List;

public interface SupplierDAO {
	public void insert(Supplier aSupplier);
	public List<Supplier> getList();
	public Supplier get(long supplierID);
}