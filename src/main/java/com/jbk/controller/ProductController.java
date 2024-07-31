package com.jbk.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.model.ProductModel;
import com.jbk.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired                             //autowired interface ko bhi laga sakte aur uske implimented  class ko bhi 
	 ProductService service;               //no need to create obj by new keyword coz of autowired

//	 1: Add product code 
	 
     @PostMapping("/add-product")
     public ResponseEntity<String> addProduct(@RequestBody @Valid ProductModel product) throws Exception  {
	 	 
		int status = service.addProduct(product);
		System.out.println(status);
		
	  return new ResponseEntity<String>("Product Added",HttpStatus.OK);
 }
     
//  2:  Get product by id 
     
      @GetMapping("/get-product-by-id/{id}")             //{}-placeholder
      public ResponseEntity<ProductModel> getProductById(@PathVariable("id") long productId)   {  
    	  
			ProductModel product = null;
			product = service.getProductById(productId);
			
	    		  
	    return new ResponseEntity<ProductModel>(product, HttpStatus.OK);    	  
    	   
      }
      
      
// 3 : Delete product by id 
      
      @DeleteMapping("/delete-product-by-id")
      public ResponseEntity<String> deleteProductById(@RequestParam ("productId") long productId){
    	
    	   int status = service.deleteProductById(productId);
    	          System.out.println(status);
    	          
    	          
    	  return new ResponseEntity<String>("Deleted",HttpStatus.OK) ;
    	  	  
      }
      
      
// 4: Update product 
      
      @PutMapping("/update-product")
      public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductModel product){
    	  
    	  int status = service.updateProduct(product);
    	  System.out.println(status);
    	 
         return new ResponseEntity<String>("updated", HttpStatus.OK);
    	  	  
      }
      
//****************************************** Criteria ************************************************************
      
//  5 : Get all products list
      
      @GetMapping("/get-all-products")
      public ResponseEntity<List<ProductModel>> getAllProducts(){
    	  
    	  List<ProductModel> productList=service.getAllProducts();
    	  if(!productList.isEmpty()) {
    		  return new ResponseEntity<List<ProductModel>>(productList,HttpStatus.OK);
    	  }
		throw new ResourceNotExistException("Product not exist: List is empty");
    	  
      }     
      
// 6 : display all products whose Products price >= 50 rupees (given price)

      //select * from product where product_price >= 50;      
      @GetMapping("products-greater-than-given-price")
      public ResponseEntity<List<ProductModel>> productGreaterThanGivenPrice(@RequestParam ("productPrice") double productPrice){
    	  
		 List<ProductModel> productList = service.productGreaterThanGivenPrice( productPrice);
    	  
		 return new ResponseEntity<List<ProductModel>>(productList,HttpStatus.OK);
      }
      
 
// 7 : display all products by their ascending order of price 
      
      @GetMapping("/order-by-price")
      public ResponseEntity<List<ProductEntity>> productOrderByPrice() {
    	  
    	  List<ProductEntity> productList=service.productOrderByPrice();
    	  
		return new  ResponseEntity<List<ProductEntity>>(productList,HttpStatus.FOUND);
    	  
      }
      
      
// 8 : Display products having name mathing given name and other results containing given name
      
      @GetMapping("/product-by-name")
      public ResponseEntity<ProductEntity> productByName(@RequestParam ("productName") String productName){
		return null;
//    	  
//    	  ProductEntity productList= service.productByName(productName);
//    	  System.out.println(productList);
    	  
//		return new ResponseEntity<List<ProductEntity>>(productList, HttpStatus.FOUND);
    	  
      }
      
      
      
// 9 : Display products having quantity <= 50 
      
      
      
      
// 10 : Display products having price between 50 - 99 
      
      
      
      
      
// 11 : Display products whose expiry year is 2023 
      
      
      
      
      
// 12 : Sort products according their ascending manufacturing date
      
      
      
      
      
// 13 : display products those name is staring from p% letter     
      
      
      
 
      
      
// 14 : display products whose name starting from and ends with 5 --> p%%5
      
      
      
      
// 15 : display products whose name startfrom and ends with n having 1 letter in between --> p_n
      
      
      
      
      
// 16 : display products having product name like "pen"
      
      
// 17  : get max price product
//     select * from product where product_price=(select max(product_price) from product);
      
      @GetMapping("/max-price-product")
      public ResponseEntity<List<ProductModel>> maxPriceProduct(){
    	  
		 List<ProductModel> productList = service.maxPriceProduct();
    	  
		 return new ResponseEntity<List<ProductModel>>(productList,HttpStatus.OK);
      }
      
// 18  : product price is between 500 and 1000  	
//       select * from product where product_price is between 500 and 1000;
      
      @GetMapping("/between-price-product/{a}/{b}")
      public ResponseEntity<List<ProductModel>> betweenPriceProduct( @PathVariable ("a") double minPrice, @PathVariable("b")double maxPrice ){
    	  
    	  List<ProductModel> product = service.betweenPriceProduct(minPrice,maxPrice);
		return new ResponseEntity<List<ProductModel>>(product, HttpStatus.OK);
    	  
      }
      
// 19 : count products in table
//      select count(producyId) from product
      @GetMapping("/count-products")
      public ResponseEntity<Integer> countProducts(){
    	  
    	  
		return new ResponseEntity<Integer>(service.countProducts(),HttpStatus.OK);
    	  
      }
//****************************************** multipart file *********************************************************      
// 19: recieving file from postman
      
      @PostMapping("/upload-file")
      public ResponseEntity<LinkedHashMap<String, Object>> uploadFile(@RequestParam MultipartFile file){
    	  
      //  To get information about file we can use methods   
    	  System.out.println(file.getName());
    	  System.out.println(file.getOriginalFilename());
    	  System.out.println(file.getSize());
    	  System.out.println(file.isEmpty());
    	  
    	  LinkedHashMap<String, Object> result = service.uploadFile(file);
    	  
		return new ResponseEntity<LinkedHashMap<String, Object>>(result,HttpStatus.OK) ;
    	  
      }
      
}

