package com.example.springdatajpatips.request;

import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CompanyRequestDTO implements Request{

	private CompanyProfile profile;

	private CompanySubscriptionCreateDetails subscrDetails;


	@Override
	public void validate() throws CustomException {
		List<CustomError> errors = new ArrayList<>();
		if(profile == null) {
			errors.add(CustomError.INVALID_COMPANY_PROFILE);
		}
		if(subscrDetails == null) {
			errors.add(CustomError.INVALID_SUBSCRIPTION_DETAILS);
		}

		//profile.validate(errors);
		//subscrDetails.validate(errors);


		try {
			profile.validate();
		} catch(CustomException e) {
			errors.addAll(e.getErrors());
		}

		try {
			subscrDetails.validate();
		} catch(CustomException e) {
			errors.addAll(e.getErrors());
		}

		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}
	}

}
