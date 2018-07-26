package com.example.springdatajpatips.request;

import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductContentDTO {

	private Long productContentId;

	private Integer count;

	public void validate() throws CustomException {
		List<CustomError> errors = new ArrayList<>();
		if (productContentId == null) {
			errors.add(CustomError.INVALID_PRODUCT_CONTENT_ID);
		}
		if (count == null || count <= 0) {
			errors.add(CustomError.INVALID_PRODUCT_CONTENT_PURCHASE_COUNT);
		}
		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}
	}


}
