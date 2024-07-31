package com.jbk.service.impl;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.impl.SupplierDaoImpl;
import com.jbk.entity.SupplierEntity;
import com.jbk.model.SupplierModel;
import com.jbk.dao.SupplierDao;
import com.jbk.service.SupplierService;
import com.jbk.validation.ObjectValidation;

@Component
public class SupplierServiceImpl implements SupplierService {
	

	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	SupplierDao dao=new SupplierDaoImpl();    //yaha pe pura new likha to bhi chalega
	
	@Override
	public int addSupplier(SupplierModel supplierModel) {
		
		SupplierEntity supplierEntity = mapper.map(supplierModel, SupplierEntity.class);
		
		return dao.addSupplier(supplierEntity);
	}

	@Override
	public SupplierEntity getSupplier(long supplierId) {
		
		
			return dao.getSupplier(supplierId);
		
	}

	@Override
	public List<SupplierEntity> getAllSuppliers() {
		
	
		return dao.getAllSuppliers();
	}

	@Override
	public int deleteSupplierById(long supplierId) {
		
		return dao.deleteSupplierById(supplierId);
	}

	/*
	 * @Override public int updateSupplier(supplierEntity supplier) {
	 * 
	 * return dao.updateSupplier(supplier); }
	 */

	/*
	 * @Override public List<SupplierEntity> supplierGreaterThanGivenPrice(double
	 * supplierPrice) {
	 * 
	 * return dao.supplierGreaterThanGivenPrice(supplierPrice); }
	 */
	/*
	 * @Override public List<SupplierEntity> supplierOrderByPrice() {
	 * 
	 * return dao.supplierOrderByPrice(); }
	 */
	@Override
	public List<SupplierEntity> SupplierByName(String supplierName) {
		
		return dao.supplierByName(supplierName);
	}

	@Override
	public int updateSupplier(SupplierEntity Supplier) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SupplierEntity> SupplierGreaterThanGivenPrice(double SupplierPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupplierEntity> SupplierOrderByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
 