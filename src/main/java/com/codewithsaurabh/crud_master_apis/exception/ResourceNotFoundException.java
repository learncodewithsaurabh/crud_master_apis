package com.codewithsaurabh.crud_master_apis.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s is not found with %s : %s", resourceName,fieldName,fieldValue));
	}
}
