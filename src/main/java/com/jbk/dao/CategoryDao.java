package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;
import com.jbk.model.CategoryModel;

public interface CategoryDao {

	public int addCategory(CategoryEntity categoryEntity);
	
	public CategoryEntity getCategoryById(long categoryId) ;

	public int deleteCategoryById(long categoryId);
	
	public int updateCategory(CategoryEntity categoryEntity);
	
	public List<CategoryEntity> getAllCategory();
	
	public List<CategoryEntity> sort(String columnName);
	
	
//	public List<CategoryEntity> categoryGreaterThanGivenPrice(double categoryPrice);
//	public List<CategoryEntity> categoryOrderByPrice();
//	public List<CategoryEntity> categoryByName(String categoryName);
}
