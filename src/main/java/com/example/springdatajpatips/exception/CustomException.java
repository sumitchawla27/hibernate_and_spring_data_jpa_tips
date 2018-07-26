package com.example.springdatajpatips.exception;

import java.util.ArrayList;
import java.util.List;

public class CustomException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	private List<CustomError> errors = null;
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(CustomError error) {
		super(error.getErrorKey());
		errors = new ArrayList<>();
		this.errors.add(error);
	}
	
	public CustomException(List<CustomError> errors) {
		super(errors.get(0).getErrorKey());
		this.errors = errors;
	}
	
	public CustomException(CustomError error, String message) {
		super(message);
		errors = new ArrayList<CustomError>();
		this.errors.add(error);
	}

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}
	
	
}
