package com.jbk.service;

import java.util.List;

import com.jbk.entity.SupplierEntity;
import com.jbk.model.SupplierModel;

public interface SupplierService {

	public int addSupplier(SupplierModel supplierModel);

	public SupplierEntity getSupplier(long supplierId);

	public List<SupplierEntity> getAllSuppliers();

	public int deleteSupplierById(long supplierId);

	public int updateSupplier(SupplierEntity supplier);

	public List<SupplierEntity> SupplierGreaterThanGivenPrice(double supplierPrice);

	public List<SupplierEntity> SupplierOrderByPrice();

	public List<SupplierEntity> SupplierByName(String supplierName);

}
