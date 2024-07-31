package com.jbk.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionResponce {
	
	private LocalDateTime time;
	private String path;
	private HttpStatus statusCode;
	private String message;
	public ExceptionResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExceptionResponce(LocalDateTime time, String path, HttpStatus statusCode, String message) {
		super();
		this.time = time;
		this.path = path;
		this.statusCode = statusCode;
		this.message = message;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ExceptionResponce [time=" + time + ", path=" + path + ", statusCode=" + statusCode + ", message="
				+ message + "]";
	}
	

}
