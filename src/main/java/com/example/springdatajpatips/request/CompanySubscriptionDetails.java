package com.example.springdatajpatips.request;



import com.example.springdatajpatips.enums.PeriodType;
import com.example.springdatajpatips.enums.SubscriptionType;
import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;

import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CompanySubscriptionDetails implements Request{

	private SubscriptionType subscriptionType;

	private Date startTs;

	private Date endTs;

	private Integer period;

	private PeriodType periodType;

	public void validate() throws CustomException {
		List<CustomError> errors = new ArrayList<>();

		if(subscriptionType == null || ! (EnumUtils.isValidEnum(SubscriptionType.class, subscriptionType.name()))) {
			errors.add(CustomError.INVALID_SUBSCRIPTION_TYPE);
		}

		if(startTs == null) {
			errors.add(CustomError.INVALID_SUBSCRIPTION_START_DATE);
		}
		if(endTs == null) {
			errors.add(CustomError.INVALID_SUBSCRIPTION_END_DATE);
		}
		if(periodType == null || ! (EnumUtils.isValidEnum(PeriodType.class, periodType.name()))){
			errors.add(CustomError.INVALID_SUBSCRIPTION_PERIOD_TYPE);
		}

		if(period == null || period <= 0) {
			errors.add(CustomError.INVALID_SUBSCRIPTION_PERIOD);
		}

		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}
	}




}
