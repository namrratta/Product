package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity 
@Table(name="product")
public class ProductEntity {
	
@Id
private long productId;

@Column(unique = true, nullable = false)
	private String productName;

@OneToOne
@JoinColumn(name="supplier_id")
	private SupplierEntity supplierEntity;

@OneToOne
@JoinColumn(name="category_id")
	private CategoryEntity categoryEntity;

@Column(unique = false, nullable = false)	
	private int productQty;

@Column(unique = false, nullable = false)
	private double productPrice;

@Column(unique = false, nullable = false)	
	private String mfgDate;

@Column(unique = false, nullable = true)
	private String expDate;

@Column(unique=false, nullable = false)
    private int deliveryCharge;

public ProductEntity() {
	super();
	// TODO Auto-generated constructor stub
}

public ProductEntity(long productId, String productName, SupplierEntity supplierEntity, CategoryEntity categoryEntity,
		int productQty, double productPrice, String mfgDate, String expDate, int deliveryCharge) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.supplierEntity = supplierEntity;
	this.categoryEntity = categoryEntity;
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

public SupplierEntity getSupplier() {
	return supplierEntity;
}

public void setSupplier(SupplierEntity supplierEntity) {
	this.supplierEntity = supplierEntity;
}

public CategoryEntity getCategory() {
	return categoryEntity;
}

public void setCategory(CategoryEntity categoryEntity) {
	this.categoryEntity = categoryEntity;
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
	return "ProductEntity [productId=" + productId + ", productName=" + productName + ", supplierEntity=" + supplierEntity
			+ ", categoryEntity=" + categoryEntity + ", productQty=" + productQty + ", productPrice=" + productPrice + ", mfgDate="
			+ mfgDate + ", expDate=" + expDate + ", deliveryCharge=" + deliveryCharge + "]";
}
	
	
	
}
