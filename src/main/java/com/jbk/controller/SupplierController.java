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

import com.jbk.entity.SupplierEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistException;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired                             //autowired interface ko bhi laga sakte aur uske implimented  class ko bhi 
	 SupplierService service;              //no need to create obj by new keyword coz of autowired

//	 1: Add supplier code 
	 
     @PostMapping("/add-supplier")
     public ResponseEntity<String> addSupplier(@RequestBody @Valid SupplierModel supplierModel) throws Exception  {
	 	 
    	 service.addSupplier(supplierModel);
	
	 
    	 return new ResponseEntity<String>("supplier added successfully", HttpStatus.OK); 
	  

 }
     
//  2:  Get supplier by id 
     
      @GetMapping("/get-supplier-by-Id/{id}")             //{}-placeholder
      public ResponseEntity<SupplierEntity> getSupplierById(@PathVariable("id") int supplierId)   {  
    	  
			SupplierEntity supplier = null;
			supplier = service.getSupplier(supplierId);
			
	    		  
	     return new ResponseEntity<SupplierEntity>(supplier, HttpStatus.OK);    	  
    	   
      }
      
//  3 : Get all suppliers list
      
      @GetMapping("/get-all-suppliers")
      public ResponseEntity<List<SupplierEntity>> getAllSuppliers(){
    	  
    	  List<SupplierEntity> supplierList=service.getAllSuppliers();
    	  if(!supplierList.isEmpty()) {
    		  return new ResponseEntity<List<SupplierEntity>>(supplierList,HttpStatus.OK);
    	  }
		throw new ResourceNotExistException("Supplier not exist: List is empty");
    	  
      }
      
// 4 : Delete supplier by id 
      
      @DeleteMapping("/delete-supplier-by-id")
      public ResponseEntity<String> deleteSupplierById(@RequestParam ("supplierId") long supplierId){
    	
    	   int status = service.deleteSupplierById(supplierId);
    	   
    	  return new ResponseEntity<String>("Deleted",HttpStatus.OK) ;
    	  	  
      }
      
      
// 5: Update supplier by Id
      
      @PutMapping("/update-supplier")
      public ResponseEntity<String> updateSupplier(@RequestBody SupplierEntity supplier){
    	  
    	  int status = service.updateSupplier(supplier);
    	  if(status==1) {
    	  
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
    	  }
    	  else
    	  {
    		  return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
    	  }
    	  
      }
      
      

 
// 7 : display all suppliers by their ascending order of price 
      
//      @GetMapping("/order-by-price")
//      public ResponseEntity<List<SupplierEntity>> supplierOrderByPrice() {
//    	  
//    	  List<SupplierEntity> supplierList=service.supplierOrderByPrice();
//    	  
//		return new  ResponseEntity<List<SupplierEntity>>(supplierList,HttpStatus.FOUND);
//    	  
//      }
      
      
// 8 : Display suppliers having name mathing given name and other results containing given name
      
  //    @GetMapping("/supplier-by-name")
  //    public ResponseEntity<List<SupplierEntity>> supplierByName(@RequestParam ("supplierName") String supplierName){
    	  
  //  	  List<SupplierEntity> supplierList= service.supplierByName(supplierName);
   // 	  System.out.println(supplierList);
    	  
	//	return new ResponseEntity<List<SupplierEntity>>(supplierList, HttpStatus.FOUND);
    	  
    //  }
      
      
      
// 9 : Display suppliers having quantity <= 50 
      
      
      
      
// 10 : Display suppliers having price between 50 - 99 
      
      
      
      
      
// 11 : Display suppliers whose expiry year is 2023 
      
      
      
      
      
// 12 : Sort suppliers according their ascending manufacturing date
      
      
      
      
      
// 13 : display suppliers those name is staring from p% letter     
      
      
      
 
      
      
// 14 : display suppliers whose name starting from and ends with 5 --> p%%5
      
      
      
      
// 15 : display suppliers whose name startfrom and ends with n having 1 letter in between --> p_n
      
      
      
      
      
// 16 : display suppliers having supplier name like "pen"
      
      
      
      
}

