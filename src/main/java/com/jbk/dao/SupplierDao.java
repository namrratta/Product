package com.jbk.dao;

import java.util.List;

import com.jbk.entity.SupplierEntity;

public interface SupplierDao {

	public int addSupplier(SupplierEntity supplier);
	public SupplierEntity getSupplier(long supplierId)  ;
	public List<SupplierEntity> getAllSuppliers();
	public int deleteSupplierById(long supplierId);
	public int updateSupplier(SupplierEntity supplier);
	public List<SupplierEntity> supplierGreaterThanGivenPrice(double supplierPrice);
	public List<SupplierEntity> supplierOrderByPrice();
	public List<SupplierEntity> supplierByName(String supplierName);
}
