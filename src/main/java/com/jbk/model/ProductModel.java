package com.jbk.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProductModel {
	
	private long productId;

	@NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$", message = "Product name must start with an alphabet and can be alphanumeric")
	private String productName;

	private SupplierModel supplierModel;

	private CategoryModel categoryModel;
	
	@Min(value=1, message= "Invalid price : must be more than 0")
	private double productPrice;
	
	@Min(value=1, message= "Invalid quantity : must be more than 0")
    private int productQty;
	
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date : should be in yyyy-mm-dd")
	private String mfgDate;
	
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid Date : should be in yyyy-mm-dd")
	private String expDate;
	
	@Min(value=1, message= "Invalid charges : must be more than 0")
    private int deliveryCharge;

public ProductModel() {
	super();
	// TODO Auto-generated constructor stub
}

public ProductModel(long productId, String productName, SupplierModel supplierModel, CategoryModel categoryModel,
		int productQty, double productPrice, String mfgDate, String expDate, int deliveryCharge) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.supplierModel = supplierModel;
	this.categoryModel = categoryModel;
	this.productQty = productQty;
	this.productPrice = productPrice;
	this.mfgDate = mfgDate;
	this.expDate = expDate;
	this.deliveryCharge = deliveryCharge;
}

public long getProductId() {
	return productId;
}

public void setProductId(long productId) {
	this.productId = productId;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public SupplierModel getSupplier() {
	return supplierModel;
}

public void setSupplier(SupplierModel supplierModel) {
	this.supplierModel = supplierModel;
}

public CategoryModel getCategory() {
	return categoryModel;
}

public void setCategory(CategoryModel categoryModel) {
	this.categoryModel = categoryModel;
}

public int getProductQty() {
	return productQty;
}

public void setProductQty(int productQty) {
	this.productQty = productQty;
}

public double getProductPrice() {
	return productPrice;
}

public void setProductPrice(double productPrice) {
	this.productPrice = productPrice;
}

public String getMfgDate() {
	return mfgDate;
}

public void setMfgDate(String mfgDate) {
	this.mfgDate = mfgDate;
}

public String getExpDate() {
	return expDate;
}

public void setExpDate(String expDate) {
	this.expDate = expDate;
}

public int getDeliveryCharge() {
	return deliveryCharge;
}

public void setDeliveryCharge(int deliveryCharge) {
	this.deliveryCharge = deliveryCharge;
}

@Override
public String toString() {
	return "ProductEntity [productId=" + productId + ", productName=" + productName + ", supplierModel=" + supplierModel
			+ ", categoryModel=" + categoryModel + ", productQty=" + productQty + ", productPrice=" + productPrice + ", mfgDate="
			+ mfgDate + ", expDate=" + expDate + ", deliveryCharge=" + deliveryCharge + "]";
}
	
	
	
}
