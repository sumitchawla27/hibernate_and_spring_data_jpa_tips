package com.example.springdatajpatips.service;

import com.example.springdatajpatips.model.Company;
import com.example.springdatajpatips.request.CompanyRequestDTO;
import com.example.springdatajpatips.response.CompanyProfileResponse;
import com.example.springdatajpatips.response.IdNameDTO;

import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface CompanyService {

	IdNameDTO addCompany(CompanyRequestDTO companyRequestDTO);

	Company fetchCompany(Long companyId);

	CompanyProfileResponse getCompanyDetails(Long companyId);

	void deleteCompany(Long company);

	CompanyProfileResponse findCompanyByName(String name);

	List<CompanyProfileResponse> getAllCompanies(PageRequest pageRequest);

	List<Long> deleteCompanies(List<Long> companyIds);
}
