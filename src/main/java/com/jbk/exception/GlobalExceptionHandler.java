package com.jbk.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	public Map<String, String> methodArg(MethodArgumentNotValidException excp){
		
		Map<String,String> errorMap=new HashMap<>();
		
		List<FieldError> fieldErrors=excp.getFieldErrors();
		
		    for (FieldError eachError : fieldErrors) {
			
			   errorMap.put(eachError.getField(), eachError.getDefaultMessage());
				
		     }	
	
	    return errorMap;
	
	} 
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public ExceptionResponce resourceExcp(ResourceAlreadyExistException ex,HttpServletRequest request) {
		
		ExceptionResponce responce=new ExceptionResponce();
		
		responce.setMessage(ex.getMessage());
		responce.setPath(request.getRequestURI());
		responce.setStatusCode(HttpStatus.CONFLICT);
		responce.setTime(LocalDateTime.now());
		
		return responce;
	}
	
	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponce somethingWentWrongException(SomethingWentWrongException ex,HttpServletRequest request) {
		
		ExceptionResponce responce=new ExceptionResponce();
		
		responce.setMessage(ex.getMessage());
		responce.setPath(request.getRequestURI());
		responce.setStatusCode(HttpStatus.CONFLICT);
		responce.setTime(LocalDateTime.now());
		
		return responce;
	}

	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseStatus(code=HttpStatus.OK)
	public ExceptionResponce resourceNotExistException(ResourceNotExistException ex,HttpServletRequest request) {
		
		ExceptionResponce responce=new ExceptionResponce();
		
		responce.setMessage(ex.getMessage());
		responce.setPath(request.getRequestURI());
		responce.setStatusCode(HttpStatus.CONFLICT);
		responce.setTime(LocalDateTime.now());
		
		return responce;
	}

}
