package com.jbk.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.entity.SupplierEntity;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.SupplierModel;
import com.jbk.service.CategoryService;
import com.jbk.service.SupplierService;

@Component
public class ObjectValidation {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	public Map<String, String> validateProduct(ProductModel productModel){
		
		Map<String ,String> errorMap = new HashMap<>();
		
		String productName = productModel.getProductName();
		double productPrice = productModel.getProductPrice();
		int productQty = productModel.getProductQty();
		String mfgDate = productModel.getMfgDate();
		String expDate = productModel.getExpDate();
		int deliveryCharge = productModel.getDeliveryCharge();
		SupplierModel supplier = productModel.getSupplier();
		CategoryModel category = productModel.getCategory();
		
		if(productName==null || productName.trim().equals("")){
			errorMap.put("product Name", "Invalid Product Name");
		}
		if(productPrice <= 0) {
			errorMap.put("Product Price", "Invalid product price");
		}
		if(productQty <= 0) {
			errorMap.put("Product Quantity", "Invalid product quantity");
		}
		if(mfgDate == null) {
			errorMap.put("mfg date", "Invalid mfg date");
		}
		if(expDate == null) {
			errorMap.put("exp date", "Invalid exp date");
		}
		if(deliveryCharge <= 0) {
			errorMap.put("Delivery charge", "Invalid Delivery Charges");
		}
		
		try {
			CategoryModel categoryById = categoryService.getCategoryById(category.getCategoryId());
		} catch (Exception e) {
			errorMap.put("category", "Category not exist");
		}
		
		try {
			SupplierEntity supplierById = supplierService.getSupplier(supplier.getSupplierId());
		} catch (Exception e) {
			errorMap.put("supplier", "Supplier not exist");
		}
		
		
		return errorMap;
		
	}

}
