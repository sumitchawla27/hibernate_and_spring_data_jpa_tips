package com.example.springdatajpatips.serviceimpl;

import com.example.springdatajpatips.enums.Status;
import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;
import com.example.springdatajpatips.model.Company;
import com.example.springdatajpatips.repository.CompanyRepository;
import com.example.springdatajpatips.request.CompanyProfile;
import com.example.springdatajpatips.request.CompanyRequestDTO;
import com.example.springdatajpatips.response.CompanyProfileResponse;
import com.example.springdatajpatips.response.IdNameDTO;
import com.example.springdatajpatips.service.CompanyService;
import com.example.springdatajpatips.service.CountryService;
import com.example.springdatajpatips.service.TerritoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ma.glasnost.orika.MapperFacade;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private MapperFacade facade;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CountryService countryService;

	@Autowired
	private TerritoryService territoryService;


	@Autowired
	private EntityManager em;

	public IdNameDTO addCompany(CompanyRequestDTO companyRequestDTO) {
		CompanyProfile companyProfile = companyRequestDTO.getProfile();
		Company duplicateCompany = findByName(companyProfile.getName());
		if (duplicateCompany == null) {
			Company company = facade.map(companyRequestDTO, Company.class);
			company.setCountry(countryService.getCountryById(companyProfile.getCountryId()));
			company.setTerritory(territoryService.getTerritoryById(companyProfile.getTerritoryId()));
			if (companyRequestDTO.getProfile().getIsLocked() == true) {
				company.setLockTs(new Timestamp(System.currentTimeMillis()));
			}
			company.setStatus(Status.ACTIVE);

			company = companyRepository.save(company);
			return new IdNameDTO(company.getId(), company.getName());
		} else {
			throw new CustomException(CustomError.DUPLICATE_COMPANY_NAME);

		}

	}


//1
//		public Company fetchCompany(Long companyId)
//	{
//		return companyRepository.findByIdAndStatus(companyId, Status.ACTIVE).orElseThrow(() -> new CustomException(CustomError.COMPANY_NOT_FOUND));
//	}

	//2
	public Company fetchCompany(Long companyId) {
		return companyRepository.findById(companyId).orElseThrow(() -> new CustomException(CustomError.COMPANY_NOT_FOUND));
	}

//3
//	public Company fetchCompany(Long companyId){
//		EntityGraph graph = em.getEntityGraph("graph.countryAndTerritory");
//		Map hints = new HashMap();
//		hints.put("javax.persistence.fetchgraph", graph);
//		Company company = em.find(Company.class, companyId, hints);
//		if(company==null){
//			throw new CustomException(CustomError.COMPANY_NOT_FOUND);
//		}
//		else{
//			return company;
//		}
//	}

	public CompanyProfileResponse getCompanyDetails(Long companyId) {
		Company company = fetchCompany(companyId);
		return mapToCompanyProfileResponse(company, new CompanyProfileResponse());
	}


	public void deleteCompany(Long companyId) {
		Company company = fetchCompany(companyId);
		companyRepository.delete(company);
		logger.info("Deleted company : {}", companyId);
	}

	public Company findByName(String name) {
		TypedQuery<Company> query = em.createNamedQuery("Company.FindByName", Company.class);
		query.setParameter("name", "%" + name + "%");
		return query.getSingleResult();
	}


	public CompanyProfileResponse findCompanyByName(String name) {
		Company company = findByName(name);
		return mapToCompanyProfileResponse(company, new CompanyProfileResponse());
	}

	public List<CompanyProfileResponse> getAllCompanies(PageRequest pageRequest) {
		Page<Company> companies = companyRepository.findAll(pageRequest);
		return companies.getContent().stream().map(company -> mapToCompanyProfileResponse(company, new CompanyProfileResponse())).collect(Collectors.toList());
	}

	public CompanyProfileResponse mapToCompanyProfileResponse(Company company, CompanyProfileResponse companyProfileResponse) {
		facade.map(company, companyProfileResponse);
		companyProfileResponse.setIsLocked(company.getLockTs() != null);
		return companyProfileResponse;
	}

	@Override
	public List<Long> deleteCompanies(List<Long> companyIds) {

		// check if given company ids are present in the DB
		List<Company> companiesInDB = companyRepository.findAllById(companyIds);
		if (companiesInDB != null && companyIds.size() != companiesInDB.size()) {
			logger.error("Some of the company ids {} not present in DB", companyIds);
			throw new CustomException(CustomError.COMPANY_NOT_FOUND);
		}

		// soft delete company profiles
//		companyRepository.deleteByIds(companyIds);
		companyRepository.deleteAll(companiesInDB);
		logger.debug("Marked as deleted Company Ids: {}", companyIds);

		return companyIds;
	}
}