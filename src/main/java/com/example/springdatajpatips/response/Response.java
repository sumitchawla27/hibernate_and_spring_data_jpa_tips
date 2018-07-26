package com.example.springdatajpatips.response;


import com.example.springdatajpatips.exception.CustomError;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Response<T> {
	
	@ApiModelProperty
	private T data;
	
	@ApiModelProperty
	private List<CustomError> errors;

	private String message;

	public String getMessage() {
		return message;
	}

	public Response<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public static <T>Response<T> getSuccessResponse(T data) {
		Response<T> response = new Response<>();
		response.data = data;
		return response;
	}
	
	public static Response<Object> getEmptySuccessResponse() {
		Response<Object> response = new Response<>();
		response.data = new Object();
		return response;
	}
	
	public static Response<Void> getErrorResponse(List<CustomError> errors) {
		Response<Void> response = new Response<>();
		response.errors = errors;
		return response;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public List<CustomError> getErrors() {
		return errors;
	}
	public void setErrors(List<CustomError> error) {
		this.errors = error;
	}
	public void setError(CustomError error) {
		errors = new ArrayList<>();
		this.errors.add(error);
	}
}
