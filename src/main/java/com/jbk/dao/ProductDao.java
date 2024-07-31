package com.jbk.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.ProductEntity;

public interface ProductDao {
//add
	public int addProduct(ProductEntity product);
//get	
	public ProductEntity getProductById(long productId)  ;
//delete	
	public int deleteProductById(long productId);
//update	
	public int updateProduct(ProductEntity product);
	
//get all 
	public List<ProductEntity> getAllProducts();
	
//display all products by their ascending order of price 
	public List<ProductEntity> productOrderByPrice();
	
//get product by name
	public ProductEntity productByName(String productName);
	
//	max price product
	public List<ProductEntity> maxPriceProduct();
	
//	between price product
	public List<ProductEntity> betweenPriceProduct(double minPrice, double maxPrice);
	
//	product greater than given price
	public List<ProductEntity> productGreaterThanGivenPrice(double productPrice);
	
//	multipart file
	public String uploadFile(MultipartFile file);
	
//	count products
	public int countProducts();
}
