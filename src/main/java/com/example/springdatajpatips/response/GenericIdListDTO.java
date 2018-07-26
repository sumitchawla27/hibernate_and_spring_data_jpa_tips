package com.example.springdatajpatips.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import lombok.Data;

@Data
public class GenericIdListDTO {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericIdListDTO.class);
	
	List<Long> ids;
	
	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String obj = null;
		try {
			obj = mapperObj.writeValueAsString(this);
			
		} catch (JsonProcessingException e) {
			logger.error("Exception: {}",e);
		}
		return obj;
	}
	
}
