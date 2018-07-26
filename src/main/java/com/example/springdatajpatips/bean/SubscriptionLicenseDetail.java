package com.example.springdatajpatips.bean;


import com.example.springdatajpatips.enums.ProductContentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionLicenseDetail {
	
	private Long prodContentId;
	private String prodContentName;
	private ProductContentType prodContentType;
	private Long subscrProdId;
	private Integer totalLicenseCount;
	private Integer availableLicenseCount;
	private Long usedLicenseCount;
	private Boolean isOverAllocated;
	
}
