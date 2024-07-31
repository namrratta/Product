package com.jbk.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class CategoryModel {
	
	@Min(value=1, message= "invalid id: should be in int")
	private long categoryId;
	
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name : must be in alphabets only can contain spaces")  
	private String categoryName;
	
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name : must be in alphabets only can contain spaces") 
	private String discription;
	
	@Min(value=1, message= "invalid discount: should be in int")
	private int discount;
	
	@Min(value=1, message= "invalid gst: should be in int")
	private int gst;
	
	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryModel(@Min(value = 1, message = "invalid id: should be in int") long categoryId,
			@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name : must be in alphabets only can contain spaces") String categoryName,
			@Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name : must be in alphabets only can contain spaces") String discription,
			@Min(value = 1, message = "invalid discount: should be in int") int discount,
			@Min(value = 1, message = "invalid gst: should be in int") int gst) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.discription = discription;
		this.discount = discount;
		this.gst = gst;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "CategoryModel [categoryId=" + categoryId + ", categoryName=" + categoryName + ", discription="
				+ discription + ", discount=" + discount + ", gst=" + gst + "]";
	}

	
	

}
