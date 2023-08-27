//package com.myblogapi.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(HttpStatus.BAD_REQUEST)
//@SuppressWarnings("serial")
//public class BlogAPIException extends RuntimeException {
//	
//	private String message;
//	private HttpStatus badRequest;
//	
//	public BlogAPIException(HttpStatus badRequest, String message) {
//		this.badRequest = badRequest;
//		this.message = message;
//	}
//	
//	public HttpStatus getBadRequest() {
//		return badRequest;
//	}
//	
//	public String getMessage() {
//		return message;
//	}
//
//}
