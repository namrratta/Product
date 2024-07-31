package com.jbk.dao.impl;

import java.util.List;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.exception.SomethingWentWrongException;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sf;

	public ProductDaoImpl() {

	}

//	 1: Add product code 

		@Override
		public int addProduct(ProductEntity productEntity) {
			try {
	
				Session session = sf.openSession();
				session.save(productEntity);
				session.beginTransaction().commit();
	
				return 1;
	
			} catch (PersistenceException e) { // ye exception tab hi aata hai jab data duplicate hota hai
					throw new ResourceAlreadyExistException("Product Already Exist");
	
			} catch (Exception e) {
					throw new SomethingWentWrongException("Something went wrong");
			}
	
		}

//  2:  Get product by id 	

	@Override
	public ProductEntity getProductById(long productId) {

		try {
			Session session = sf.openSession();
			ProductEntity productEntity = session.get(ProductEntity.class, productId);
			return productEntity;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while getting product");
		}

	}

//	3 - delete product by id

	@Override
	public int deleteProductById(long productId) {
		try {
			Session session = sf.openSession();
			ProductEntity product = session.get(ProductEntity.class, productId);
			if (product != null) {

				session.delete(product);
				session.beginTransaction().commit();
				return 1;
			}
//			else {
//				throw new ResourceNotExistException("Product not found with id "+productId);
//			}
//			
//		} catch (ResourceNotExistException e) {
//			
//			throw new ResourceNotExistException("Product not found with id "+productId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while getting product");
		}

		throw new ResourceNotExistException("Product not found with id " + productId);
	}

//   4: Update product by Id

	@Override
	public int updateProduct(ProductEntity productEntity) {
		System.out.println(productEntity);
		try {
			Session session = sf.openSession();

			ProductEntity productById = getProductById(productEntity.getProductId());

			if (productById != null) {
				session.update(productEntity);
				session.beginTransaction().commit();
				return 1;
			}

		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong exception");
		}

		throw new ResourceNotExistException("Product not Exist to update");
	}

//****************************************** Criteria ************************************************************
//	5 - get all products

	@Override
	public List<ProductEntity> getAllProducts() {
		try {
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			List list = criteria.list();
			return list;

		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong exception");
		}

	}

// 6 : display all products whose Products price >= 50 rupees (given price)	

	@Override
	public List<ProductEntity> productGreaterThanGivenPrice(double productPrice) {

		try {
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			List list = criteria.add(Restrictions.gt("productPrice", productPrice)).list();
			return list;

		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}

	}

// 7 : display all products by their ascending order of price 

	@Override
	public List<ProductEntity> productOrderByPrice() {
		return null;
//	try {
//		  List<Product> sortedlist = list.stream()
//                                     .sorted(Comparator.comparingDouble(Product::getProductPrice))
//                                     .collect(Collectors.toList());
//		 
//			if(!sortedlist.isEmpty()) {
//				return sortedlist;
//			}
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		throw new ResourceNotExistException("Product not found : list is empty");
	}

	@Override
	public ProductEntity productByName(String productName) {
		
		List<ProductEntity> list=null;
		ProductEntity productEntity=null;
		 
		try {
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			list = criteria.add(Restrictions.eq("productName", productName)).list();
			
			if(!list.isEmpty()) {
				productEntity = list.get(0);
			}
//			return product;

		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return productEntity;

}

//	max price product 

	@Override
	public List<ProductEntity> maxPriceProduct() {
		try {
			Session session = sf.openSession();

			Query<ProductEntity> query = session.createQuery(
					"from ProductEntity p where p.productPrice = (select max(p2.productPrice) from ProductEntity p2)",
					ProductEntity.class);
			List<ProductEntity> products = query.getResultList();

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
	}

//	: product price is between 500 and 1000

	@Override
	public List<ProductEntity> betweenPriceProduct(double minPrice, double maxPrice) {
		try {

			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.add(Restrictions.between("productPrice", minPrice, maxPrice));
			List list = criteria.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}

	}
// 19 : count products in table
	
	@Override
	public int countProducts() {
		try {
			Session session = sf.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.setProjection(Projections.rowCount());
			
			Long count = (Long) criteria.uniqueResult();
	        return count != null ? count.intValue() : 0; // Handle null count
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
		
		
	}

//****************************************** multipart file ************************************************************
// 19: Receiving file from postman

	@Override
	public String uploadFile(MultipartFile file) {
		return null;
//		
//		try {
//
//			Session session = sf.openSession();
//			session.save(file);
//			session.beginTransaction().commit();
//			
//			int totalCount = countProducts();
//
//
//			return "done";
//
//		} catch (PersistenceException e) { 
//			throw new ResourceAlreadyExistException("Product Already Exist");
//
//		} catch (Exception e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}
//		
	}



}
