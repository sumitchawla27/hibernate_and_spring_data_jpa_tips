package com.example.springdatajpatips.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsedLicensesCountDTO {

	private Long subscriptionProductId;

	private Long usedLicensesCount;
}
