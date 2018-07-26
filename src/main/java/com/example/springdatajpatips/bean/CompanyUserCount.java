package com.example.springdatajpatips.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyUserCount {

	Long companyId;
	Long userCount;
}
