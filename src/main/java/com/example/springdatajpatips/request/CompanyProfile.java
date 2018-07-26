package com.example.springdatajpatips.request;


import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CompanyProfile implements Request{

	private String name;

	private String parentCompanyName;

	private String groupId;

	private Long territoryId;

	private Long countryId;

	private String state;

	private String city;

	private String companyUrl;

	private String companyDomain;

	private Boolean sendEmailToTerritoryAdmin = false;

	private Boolean isLocked = false;


	@Override
	public void validate() throws CustomException {

		List<CustomError> errors = new ArrayList<>();

		if (StringUtils.isEmpty(city)) {
			errors.add(CustomError.INVALID_CITY);
		}
		if (StringUtils.isEmpty(parentCompanyName)) {
			errors.add(CustomError.INVALID_PARENT_COMPANY_NAME);
		}

		if (sendEmailToTerritoryAdmin == null) {
			errors.add(CustomError.INVALID_SEND_EMAIL_TO_TERRITORY_ADMIN_VALUE);
		}

		if (isLocked == null) {
			errors.add(CustomError.INVALID_COMPANY_LOCK_VALUE);
		}

		if (countryId==null) {
			errors.add(CustomError.INVALID_COUNTRY);
		}

		if (territoryId==null) {
			errors.add(CustomError.INVALID_TERRITORY);
		}

		if (StringUtils.isEmpty(groupId)) {
			errors.add(CustomError.INVALID_COMPANY_GROUP_ID);
		}

		if (StringUtils.isEmpty(name)) {
			errors.add(CustomError.INVALID_COMPANY_NAME);
		}

		if (StringUtils.isEmpty(companyDomain)) {
			errors.add(CustomError.INVALID_COMPANY_DOMAIN);
		}

		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}

	}
}
