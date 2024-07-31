package com.jbk.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.impl.CategoryDaoImpl;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.model.CategoryModel;
import com.jbk.dao.CategoryDao;
import com.jbk.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao dao = new CategoryDaoImpl(); // yaha pe pura new likha to bhi chalega

	@Autowired
	private ModelMapper mapper;

//	 1: Add new category

	@Override
	public int addCategory(CategoryModel categoryModel) {

		CategoryEntity categoryEntity = mapper.map(categoryModel, CategoryEntity.class);

		return dao.addCategory(categoryEntity);

	}

//	2: Get category by id 

	public CategoryModel getCategoryById(long categoryId) {

		CategoryEntity categoryEntity = dao.getCategoryById(categoryId);

		if (categoryEntity != null) {

			CategoryModel categoryModel = mapper.map(categoryEntity, CategoryModel.class);

			return categoryModel;
		} else {

			throw new ResourceNotExistException("category not exist exception");
		}
	}

	// 3 : Delete category by id

	@Override
	public int deleteCategoryById(long categoryId) {

		return dao.deleteCategoryById(categoryId);
	}

//	  4: Update category by Id

	@Override
	public int updateCategory(CategoryModel categoryModel) {

		CategoryEntity categoryEntity = mapper.map(categoryModel, CategoryEntity.class);

		return dao.updateCategory(categoryEntity);
	}

//  5 : Get All Categories 

	@Override
	public List<CategoryModel> getAllCategory() {

		List<CategoryEntity> categoryEntityList = dao.getAllCategory(); // dao ki method ko call kia jo list return
																		// karegi

		List<CategoryModel> list = new ArrayList<CategoryModel>(); // ek nayi arraylist banayi CategoryModel Type ki

		if (!categoryEntityList.isEmpty()) { // doa se aayi list agar empty nai h

			for (CategoryEntity categoryEntity : categoryEntityList) { // to use iterate kia

				CategoryModel categoryModel = mapper.map(categoryEntity, CategoryModel.class); // har ek entity ko
																								// iterate kia
				list.add(categoryModel); // list me add kia jo return karni hai

			}
			return list;
		}
		throw new ResourceNotExistException("Category is null");
	}

// 6 : Select * from category order by {any column} 

	@Override
	public List<CategoryModel> sort(String columnName) {

		List<CategoryEntity> sortedList = dao.sort(columnName);
		List<CategoryModel> list = new ArrayList<CategoryModel>();

		if (!sortedList.isEmpty()) {
			for (CategoryEntity categoryEntity : sortedList) {
				CategoryModel categoryModel = mapper.map(categoryEntity, CategoryModel.class);
				list.add(categoryModel);

			}

		}
		return list;

	}

	

//

//

//
//	@Override
//	public List<CategoryEntity> categoryGreaterThanGivenPrice(double categoryPrice) {
//		
//		return dao.categoryGreaterThanGivenPrice(categoryPrice);
//	}
//
//	@Override
//	public List<CategoryEntity> categoryOrderByPrice() {
//	
//		return dao.categoryOrderByPrice();
//	}
//
//	@Override
//	public List<CategoryEntity> categoryByName(String categoryName) {
//		
//		return dao.categoryByName(categoryName);
//	}
//	
//	

}
