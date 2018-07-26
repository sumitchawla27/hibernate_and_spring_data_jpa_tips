package com.example.springdatajpatips.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileResponse {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyProfileResponse.class);

	private Long id;
	
	private String name;

	private String parentCompanyName;

	private String groupId;

	private String territory;
	
	private Integer territoryId;

	private String country;
	
	private Integer countryId;

	private String state;
	
	private String city;

	private String companyUrl;

	private String companyDomain;

	private Boolean isLocked;

	private Boolean sendEmailToTerritoryAdmin;
	
	private Long createdTs;
	
	private Integer companyAdminCnt;
	
	private Integer totalUsers;

	private Integer registeredUsers;

	private String companyDomainRemovalRelatedMsg;
	
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
