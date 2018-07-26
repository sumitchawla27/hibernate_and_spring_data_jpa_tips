package com.example.springdatajpatips.request;


import com.example.springdatajpatips.exception.CustomException;

public interface Request {
	
	public void validate() throws CustomException;
}

