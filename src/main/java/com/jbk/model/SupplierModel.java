package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SupplierModel {
	@Min(value=1, message= "invalid id: should be in int")
	private long supplierId;
	
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name : must be in alphabets only can contain spaces") 
	private String supplierName;
	
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid city : must be in alphabets only can contain spaces") 
	private String city;
	
	@Min(value=100000, message= "invalid postal code")
	@Max(value=999999, message= "invalid postal code")
	private int postalCode;
	
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid city : must be in alphabets only can contain spaces")
	private String country;

	@Pattern(regexp = "^[1-9]\\d{9}$", message = "Invalid mobile number : provide 10 digit not start with 0")
	private String mobile;

	
	public SupplierModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierModel(int supplierId, String supplierName, String city, int postalCode, String country,
			String mobile) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobile = mobile;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "SupplierEntity [supplierId=" + supplierId + ", supplierName=" + supplierName + ", city=" + city
				+ ", postalCode=" + postalCode + ", country=" + country + ", mobile=" + mobile + "]";
	}
	
	

}
