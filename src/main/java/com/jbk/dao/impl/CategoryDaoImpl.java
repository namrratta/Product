package com.jbk.dao.impl;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;

import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;

@Component
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sf;

	public CategoryDaoImpl() {

	}

//	 1: Add new category

	@Override
	public int addCategory(CategoryEntity categoryEntity) {
		try {

			Session session = sf.openSession();
			session.save(categoryEntity);
			session.beginTransaction().commit();

			return 1;

		} catch (PersistenceException e) { // ye exception tab hi aata hai jab data duplicate hota hai
			throw new ResourceAlreadyExistException("Category Already Exist");

		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}

	}

//	2: Get category by id 

	@Override
	public CategoryEntity getCategoryById(long categoryId) {

		try {
			Session session = sf.openSession();
			CategoryEntity categoryEntity = session.get(CategoryEntity.class, categoryId);

			return categoryEntity;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while getting category");
		}

	}

//	3 - delete category by id

	@Override
	public int deleteCategoryById(long categoryId) {

		try {

			Session session = sf.openSession();
			CategoryEntity categoryEntity = session.get(CategoryEntity.class, categoryId);

			if (categoryEntity != null) {

				session.delete(categoryEntity);
				session.beginTransaction().commit();
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}

		throw new ResourceNotExistException("Category not exist ");
	}

//  4: Update category (by Id)

	@Override
	public int updateCategory(CategoryEntity categoryEntity) {
		Session session = sf.openSession();
		try {
			CategoryEntity categoryEntityNew = getCategoryById(categoryEntity.getCategoryId());

			                                                                                 // yahape existing get
																								// category by id method
																								// use ki
																								// jisase naya session
																								// nai use karna pada

			if (categoryEntityNew != null) {

				session.beginTransaction();
				session.update(categoryEntity);
				session.getTransaction().commit();
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong exception");
		}

		throw new ResourceNotExistException("Category not Exist to update");
	}

//	3 - get all categorys

	@Override
	public List<CategoryEntity> getAllCategory() {
		try {
			
			Session session=sf.openSession();
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			List<CategoryEntity> list = criteria.list();
			
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong exception");
		}
	}

	

	
// 6 : Select * from category order by {any column}
	
	@Override
	public List<CategoryEntity> sort(String columnName) {
		try {
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			
			List<CategoryEntity> sortedList = criteria.addOrder(Order.asc(columnName)).list();  //imp step
			

			return sortedList;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong exception");
		}
		
	}


	
	
	
	
	
	
	
	
//
//	@Override
//	public List<CategoryEntity> categoryGreaterThanGivenPrice(double categoryPrice) {
//		return null;
//		ArrayList<Category> newlist1=new ArrayList<>();
//		try {
//			List<Category> newlist = list.stream()
//	                               .filter(category -> category.getCategoryPrice() > categoryPrice)
//	                               .collect(Collectors.toList());
//			       
//			if(!newlist.isEmpty()) {
//				return newlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Category not found");
//	}
//
//	@Override
//	public List<CategoryEntity> categoryOrderByPrice() {
//		return null;
//	try {
//		  List<Category> sortedlist = list.stream()
//                                     .sorted(Comparator.comparingDouble(Category::getCategoryPrice))
//                                     .collect(Collectors.toList());
//		 
//			if(!sortedlist.isEmpty()) {
//				return sortedlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Category not found : list is empty");
//	}
//
//	@Override
//	public List<CategoryEntity> categoryByName(String categoryName) {
//		return null;
//		try {
//			
//			List<Category> newlist = list.stream()
//			        .filter(category -> category.getCategoryName().toLowerCase().contains(categoryName) || category.getCategoryName().toLowerCase().matches(categoryName+"[^\\w]*"))
//			        .collect(Collectors.toList());
//			
//			if(!newlist.isEmpty()) {
//				return newlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Category not found : list is empty");
//	}

}
