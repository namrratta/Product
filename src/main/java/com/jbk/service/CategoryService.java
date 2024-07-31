package com.jbk.service;

import java.util.List;

import com.jbk.model.CategoryModel;

public interface CategoryService {
	
    public int addCategory(CategoryModel categoryModel) ;
	
	public CategoryModel getCategoryById(long categoryId) ;

	public int deleteCategoryById(long categoryId);
	
	public int updateCategory(CategoryModel categoryModel);
	
	
	public List<CategoryModel> getAllCategory();
	
	public List<CategoryModel> sort(String columnName);
	
//
//	public List<CategoryModel> categoryGreaterThanGivenPrice(double categoryPrice);
//
//	public List<CategoryModel> categoryOrderByPrice();
//
//	public List<CategoryModel> categoryByName(String categoryName);
//		
}
