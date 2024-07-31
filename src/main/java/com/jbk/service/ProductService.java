package com.jbk.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.ProductEntity;
import com.jbk.model.ProductModel;

public interface ProductService {
//add	
	public int addProduct(ProductModel product) ;
//get	
	public ProductModel getProductById(long productId) ;
//delete
	public int deleteProductById(long productId);
//update	
	public int updateProduct(ProductModel product);
	
	
//get all	
	public List<ProductModel> getAllProducts();
	
//display all products whose Products price >= 50 rupees (given price)		
	public List<ProductModel> productGreaterThanGivenPrice(double productPrice);
	
//display all products by their ascending order of price 
	public List<ProductEntity> productOrderByPrice();
	
// get product by name
	public ProductModel productByName(String productName);
	
//	max price product
	public List<ProductModel> maxPriceProduct();
	
//	between price product
	public List<ProductModel> betweenPriceProduct(double minPrice, double maxPrice);
	
// 19: recieving file from postman	
	public LinkedHashMap<String, Object> uploadFile(MultipartFile file);
	
//	20: count products
	public int countProducts();
	

}
