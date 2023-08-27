package com.myblogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private String resource;
	private String fieldName;
	private long fieldId;
	
	public ResourceNotFoundException(String resource, String fieldName, long fieldId) {
		super(String.format("%s is not found with %s : %s", resource, fieldName, fieldId));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}

	public String getResource() {
		return resource;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFieldId() {
		return fieldId;
	}
	

}
