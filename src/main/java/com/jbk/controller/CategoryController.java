package com.jbk.controller;

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

import com.jbk.entity.CategoryEntity;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired                             //autowired interface ko bhi laga sakte aur uske implimented  class ko bhi 
	 CategoryService service;              //no need to create obj by new keyword coz of autowired
	
//*********************************  SESSION  *************************************************************************
//	 1: Add new category  
	 
     @PostMapping("/add-category")
     public ResponseEntity<String> addCategory(@RequestBody @Valid CategoryModel categoryModel) throws Exception  {
	 	 
	service.addCategory(categoryModel);

	
	return new ResponseEntity<String>("Added category", HttpStatus.OK);  
		
 }
     
//   2:  Get category by id code
  
    @GetMapping("/get-category-by-id/{id}")             //{}-placeholder
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("id") int categoryId)   {  
  	  
			CategoryModel categoryModel = service.getCategoryById(categoryId);
			
			return new ResponseEntity<CategoryModel>(categoryModel, HttpStatus.OK);  
    }
    
    
 //   3 : Delete category by id 
    
    @DeleteMapping("/delete-category-by-id/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@RequestParam ("categoryId") int categoryId){
  	
  	   service.deleteCategoryById(categoryId);
  	   
  	  return new ResponseEntity<String>("Deleted",HttpStatus.OK) ;
  	  	  
    }
    
//  4: Update category by Id
  
  @PutMapping("/update-category")
  public ResponseEntity<String> updateCategory(@RequestBody CategoryModel categoryModel){
	  
	   service.updateCategory(categoryModel);
	  
	return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
	  
	  
  }   
  
//*********************************  CRITERIA  *************************************************************************  
//5 : Get All Categories 
   
   @GetMapping("/get-all-category")
   public ResponseEntity<List<CategoryModel>> getAllCategory(){
 	  
 	
 		  return new ResponseEntity<List<CategoryModel>>(service.getAllCategory(),HttpStatus.OK);  
   }
     
// 6 : Select * from category order by {any given column}
      
    @GetMapping("/sort/{columnName}")
    public ResponseEntity<List<CategoryModel>> sort(@PathVariable String columnName){
    	
    	
		return new ResponseEntity<List<CategoryModel>>(service.sort(columnName),HttpStatus.OK);
    	
    }

//      
//      

//      
//      
//// 6 : display all categorys whose Categorys price >= 50 rupees (given price)
//      @GetMapping("categorys-greater-than-given-price")
//      public ResponseEntity<Object> categoryGreaterThanGivenPrice(@RequestParam ("categoryPrice") double categoryPrice){
//    	  
//		 List<CategoryEntity> categoryList = service.categoryGreaterThanGivenPrice( categoryPrice);
//    	  
//		  return new ResponseEntity<Object>(categoryList, HttpStatus.FOUND);
//      }
//      
// 
//// 7 : display all categorys by their ascending order of price 
//      
//      @GetMapping("/order-by-price")
//      public ResponseEntity<List<CategoryEntity>> categoryOrderByPrice() {
//    	  
//    	  List<CategoryEntity> categoryList=service.categoryOrderByPrice();
//    	  
//		return new  ResponseEntity<List<CategoryEntity>>(categoryList,HttpStatus.FOUND);
//    	  
//      }
//      
//      
//// 8 : Display categorys having name mathing given name and other results containing given name
//      
//      @GetMapping("/category-by-name")
//      public ResponseEntity<List<CategoryEntity>> categoryByName(@RequestParam ("categoryName") String categoryName){
//    	  
//    	  List<CategoryEntity> categoryList= service.categoryByName(categoryName);
//    	  System.out.println(categoryList);
//    	  
//		return new ResponseEntity<List<CategoryEntity>>(categoryList, HttpStatus.FOUND);
//    	  
//      }
//      
//      
//      
//// 9 : Display categorys having quantity <= 50 
//      
//      
//      
//      
//// 10 : Display categorys having price between 50 - 99 
//      
//      
//      
//      
//      
//// 11 : Display categorys whose expiry year is 2023 
//      
//      
//      
//      
//      
//// 12 : Sort categorys according their ascending manufacturing date
//      
//      
//      
//      
//      
//// 13 : display categorys those name is staring from p% letter     
//      
//      
//      
// 
//      
//      
//// 14 : display categorys whose name starting from and ends with 5 --> p%%5
//      
//      
      
      
// 15 : display categorys whose name startfrom and ends with n having 1 letter in between --> p_n
      
      
      
      
      
// 16 : display categorys having category name like "pen"
      
      
      
      
}
