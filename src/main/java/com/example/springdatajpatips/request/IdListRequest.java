package com.example.springdatajpatips.request;

import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import lombok.Data;

@Data
public class IdListRequest implements Request {

	private static final Logger logger = LoggerFactory.getLogger(IdListRequest.class);

	List<Long> ids;

	public void validate() throws CustomException {
		if(ids == null || ids.isEmpty()) {
			throw new CustomException(CustomError.INVALID_ID_LIST);
		}
	}

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

