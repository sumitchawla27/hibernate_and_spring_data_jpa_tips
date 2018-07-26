package com.example.springdatajpatips.request;

import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanySubscriptionCreateDetails extends CompanySubscriptionDetails{

	private List<ProductContentDTO> licenses;

	public void validate() throws CustomException {
		List<CustomError> errors = new ArrayList<>();

		try {
			super.validate();
		} catch(CustomException e) {
			errors.addAll(e.getErrors());
		}

		if(licenses == null || licenses.isEmpty()) {
			errors.add(CustomError.LICENSES_CANNOT_BE_NULL_OR_EMPTY);
		}

		for (ProductContentDTO productContentDTO : licenses) {
			try {
				productContentDTO.validate();
			}
			catch (CustomException e) {
				errors.addAll(e.getErrors());
			}
		}


		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}
	}

}
