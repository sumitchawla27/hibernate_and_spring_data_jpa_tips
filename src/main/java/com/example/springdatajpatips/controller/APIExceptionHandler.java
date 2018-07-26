package com.example.springdatajpatips.controller;



import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;
import com.example.springdatajpatips.response.ErrorDTO;
import com.example.springdatajpatips.response.Response;
import com.example.springdatajpatips.response.ResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class APIExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(APIExceptionHandler.class);

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	void handleException(AccessDeniedException e) {
		logger.error("Unauthorized request", e);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	 Response<Void> handleException(HttpMessageNotReadableException e) {
		logger.error("Invalid Request JSON ", e);
		ArrayList<CustomError> errors = new ArrayList<CustomError>();
		errors.add(CustomError.MALFORMED_JSON_EXCEPTION);
		Response<Void> response = Response.getErrorResponse(errors);
		response.setMessage(e.getLocalizedMessage());
		return response;
	}
	
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	void handleException(MissingServletRequestParameterException e) {
		logger.error("Invalid Request Parameters ", e);
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	void handleException(HttpRequestMethodNotSupportedException e) {
		logger.error("Invalid Http Method ", e);
	}
	
	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	Response<Void> handleException(Throwable e) {
		logger.error("Internal Error", e);
		ArrayList<CustomError> errors = new ArrayList<CustomError>();
		errors.add(CustomError.INTERNAL_SERVER_ERROR);
		return Response.getErrorResponse(errors);
	}
	
	@ExceptionHandler(value = CustomException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Response<Void> handleException(CustomException ziException) {
		logger.error("Application specific error: {}", ziException.getErrors().get(0).getErrorKey());
		return Response.getErrorResponse(ziException.getErrors());
	}



	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseDTO<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ResponseDTO<ErrorDTO> result = new ResponseDTO<ErrorDTO>();
		result.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		StringBuffer buffer = new StringBuffer();
		ex.getBindingResult().getFieldErrors().forEach(x -> buffer.append(x.getField() + " " + x.getDefaultMessage() + ","));
		result.setMessage(buffer.toString().substring(0, buffer.toString().length() - 1));
		result.setCode(1000);
		return result;
	}

}