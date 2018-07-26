package com.example.springdatajpatips.controller;


import com.example.springdatajpatips.constants.CommonApiUrl;
import com.example.springdatajpatips.constants.CompanyApiUrl;
import com.example.springdatajpatips.request.CompanyRequestDTO;
import com.example.springdatajpatips.request.IdListRequest;
import com.example.springdatajpatips.request.PagingDTO;
import com.example.springdatajpatips.response.CompanyProfileResponse;
import com.example.springdatajpatips.response.GenericIdListDTO;
import com.example.springdatajpatips.response.IdNameDTO;
import com.example.springdatajpatips.response.Response;
import com.example.springdatajpatips.service.CompanyService;
import com.example.springdatajpatips.util.PageRequestUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonApiUrl.API_VERSION_1)
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@PostMapping(CompanyApiUrl.COMPANY)
	public Response<IdNameDTO> createCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
		companyRequestDTO.validate();

		IdNameDTO idNameDto = companyService.addCompany(companyRequestDTO);
		return Response.getSuccessResponse(idNameDto);
	}


//	@PutMapping(CompanyApiUrl.COMPANY_WITH_ID)
//	public Response<CompanyProfileResponse> modifyCompany(@PathVariable("companyId") Long companyId,
//																												@RequestBody CompanyProfile companyProfile, @RequestParam Boolean confirmCompanyDomainRemoval) {
//		companyProfile.validate();
//		CompanyProfileResponse companyProfileResponse = companyService.modifyCompany(companyId, companyProfile,
//				confirmCompanyDomainRemoval);
//		return Response.getSuccessResponse(companyProfileResponse);
//	}
//
//	@GetMapping(CompanyApiUrl.COMPANY_ADMINS)
//	public Response<GenericListDTO<UserProfile>> listCompanyAdmins(@PathVariable("companyId") Long companyId) {
//		return Response.getSuccessResponse(companyService.listCompanyAdminsWrapper(companyId));
//	}

	@GetMapping(CompanyApiUrl.COMPANY_WITH_ID)
	public Response<CompanyProfileResponse> getCompany(@PathVariable("companyId") Long companyId) {
		return Response.getSuccessResponse(companyService.getCompanyDetails(companyId));
	}

	@GetMapping(CompanyApiUrl.COMPANY)
	public Response<List<CompanyProfileResponse>> getAllCompanies(PagingDTO pagingDTO) {
		PageRequest pageRequest = PageRequestUtility.createPageRequest(pagingDTO);
		return Response.getSuccessResponse(companyService.getAllCompanies(pageRequest));
	}

	@GetMapping(CompanyApiUrl.COMPANY_WITH_NAME_PARAM)
	public Response<CompanyProfileResponse> getCompanyByName(@PathVariable("name") String name) {
		return Response.getSuccessResponse(companyService.findCompanyByName(name));
	}

	@DeleteMapping(CompanyApiUrl.COMPANY_WITH_ID)
	public void deleteSingleCompany(@PathVariable("companyId") Long companyId) {
		companyService.deleteCompany(companyId);
	}






	/**
	 * Search company details based on filter criteria
	 * 
	 * @param request
	 *            - HttpRequest
	 * @param territoryId
	 *            - Territory Id
	 * @param subscrStatus
	 *            - ALL: 0 | ACTIVE_FREE_TRIAL_OR_PAID_SUBSCRIPTIONS: 1 |
	 *            ACTIVE_PAID_SUBSCRIPTIONS: 2 | ACTIVE_FREE_TRIAL: 3 |
	 *            EXPIRED_FREE_TRIAL: 4 | EXPIRED_PAID_SUBSCRIPTIONS: 5 |
	 *            FUTURE_SUBSCRIPTIONS: 6
	 * @param nameOrGroupId
	 *            - Company name or group id
	 * @param pagingDTO
	 *            - Pagination page no and size.
	 * @return List of searched company details
	 */
//	@GetMapping(CompanyApiUrl.COMPANY)
//	public Response<GenericListDTO<SearchCompanyDTO>> searchCompany(HttpServletRequest request,
//																																	@RequestParam(required = false) Integer territoryId,
//																																	@RequestParam(required = false) SubscriptionStatus subscrStatus,
//																																	@RequestParam(required = false) String nameOrGroupId, PagingDTO pagingDTO) {
//
//		logger.info("Received search company request: {}", request.getParameterMap().entrySet().stream()
//				.map(entry -> entry.getKey() + ": " + entry.getValue()[0]).collect(Collectors.joining(", ")));
//		PageRequest pageRequest = PageRequestUtility.createPageRequest(pagingDTO);
//		GenericListDTO<SearchCompanyDTO> searchCompanyResponse = companyService.searchCompanies(territoryId,
//				subscrStatus, nameOrGroupId, pageRequest);
//		logger.info("# searched companies: {}", searchCompanyResponse.getTotalCount());
//		return Response.getSuccessResponse(searchCompanyResponse);
//	}

	/**
	 * Soft-Deletes given company ids
	 * @param deleteCompanyRequest
	 *            - List of company ids to be deleted
	 * @return - List of deleted company ids
	 */
	@DeleteMapping(CompanyApiUrl.COMPANY)
	public Response<GenericIdListDTO> deleteCompanies(@RequestBody IdListRequest deleteCompanyRequest) {

		logger.info("Received delete company request: {}", deleteCompanyRequest);

		deleteCompanyRequest.validate();

		List<Long> deletedCompanyIds = companyService.deleteCompanies(deleteCompanyRequest.getIds());
		GenericIdListDTO deletedCompanyResponse = new GenericIdListDTO();
		deletedCompanyResponse.setIds(deletedCompanyIds);

		logger.info("Delete company response: {}", deletedCompanyResponse);
		return Response.getSuccessResponse(deletedCompanyResponse);

	}

	/**
	 * Search Company users based on filter criteria
	 * 
	 * @param request
	 *            - Http Request
	 * @param companyId
	 *            - Company Id
	 * @param userNameOrEmail
	 *            - User name or email
	 * @param pagingDTO
	 *            - Pagination page no and size.
	 * @return List of searched company user details
	 */
//	@GetMapping(CompanyApiUrl.COMPANY_USER)
//	public Response<GenericListDTO<SearchCompanyUserDTO>> searchCompanyUsers(HttpServletRequest request,
//																																					 @PathVariable long companyId, @RequestParam(required = false) String userNameOrEmail,
//																																					 PagingDTO pagingDTO) {
//
//		logger.info("Received search company user request: companyId: {}, filter: {}", companyId,
//				request.getParameterMap().entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()[0])
//						.collect(Collectors.joining(", ")));
//		PageRequest pageRequest = PageRequestUtility.createPageRequest(pagingDTO);
//		GenericListDTO<SearchCompanyUserDTO> searchCompanyUserResponse = companyService.searchCompanyUsers(companyId,
//				userNameOrEmail, pageRequest);
//		logger.info("# searched company users: {}", searchCompanyUserResponse.getTotalCount());
//		return Response.getSuccessResponse(searchCompanyUserResponse);
//	}

	/**
	 * Get user profile and license details for given company user
	 * 
	 * @param companyId
	 *            - Company Id
	 * @param companyUserId
	 *            - Company User Id
	 * @return - User profile details along with license details
	 */
//	@GetMapping(CompanyApiUrl.COMPANY_USER_DETAILS)
//	public Response<CompanyUserDTO> getCompanyUserDetails(@PathVariable long companyId,
//																												@PathVariable long companyUserId) {
//
//		logger.info("Received get company user request: companyId: {}, companyUserId: {}", companyId, companyUserId);
//
//		CompanyUserDTO companyUserDTO = companyService.getCompanyUserDetails(companyId, companyUserId);
//		logger.info("company user details: {}", companyUserDTO);
//		return Response.getSuccessResponse(companyUserDTO);
//	}

	/**
	 * Get user profile and license details for given company user
	 *
	 * @param companyId
	 *            - Company Id
	 * @param companyUserId
//	 *            - Company User Id
//	 * @return - User profile details along with license details
//	 */
//	@PutMapping(CompanyApiUrl.COMPANY_USER_DETAILS)
//	public Response<CompanyUserDTO> modifyCompanyUserDetails(@PathVariable long companyId,
//																													 @PathVariable long companyUserId, @RequestBody ModifyCompanyUserRequestDTO modifycompanyUserRequest) {
//
//		logger.info("Received modify company user request: companyId: {}, companyUserId: {}, companyUser: {}",
//				companyId, companyUserId, modifycompanyUserRequest);
//		modifycompanyUserRequest.validate();
//		CompanyUserDTO companyUserDTO = companyService.modifyCompanyUserDetails(companyId,
//				modifycompanyUserRequest.getSubscriptionId(), companyUserId, modifycompanyUserRequest.getUserProfile(),
//				modifycompanyUserRequest.getLicenses());
//		logger.info("Modified company user details: {}", companyUserDTO);
//		return Response.getSuccessResponse(companyUserDTO);
//	}
//
	/**
	 * Soft-Deletes given company users
	 * @param deleteCompanyUserRequest
	 *            - List of company user ids to be deleted
	 * @return - List of deleted company user ids
	 */
//	@DeleteMapping(CompanyApiUrl.COMPANY_USER)
//	public Response<GenericIdListDTO> deleteCompanyUsers(@PathVariable long companyId,
//			@RequestBody IdListRequest deleteCompanyUserRequest) {
//
//		logger.info("Received delete company user request : {} for company {}", deleteCompanyUserRequest, companyId);
//
//		deleteCompanyUserRequest.validate();
//		// TODO: Pass logged in user id
//		List<Long> deletedCompanyUserIds = companyService.deleteCompanyUsers(companyId, deleteCompanyUserRequest.getIds(), 1L);
//		GenericIdListDTO deletedCompanyResponse = new GenericIdListDTO();
//		deletedCompanyResponse.setIds(deletedCompanyUserIds);
//
//		logger.info("Delete company user response: {}", deletedCompanyResponse);
//		return Response.getSuccessResponse(deletedCompanyResponse);
//	}

	/**
	 * Validates if user email can be added to the company
	 * @param companyId - Company Id
	 * @param validateEmailRequest - request containing user email
	 * @return
	 */
//	@PostMapping(CompanyApiUrl.VALIDATE_COMPANY_USER_EMAIL)
//	public Response<CompanyUserDTO> validateCompanyUserEmail(@PathVariable long companyId,
//			@RequestBody ValidateEmailRequestDTO validateEmailRequest) {
//
//		logger.info("Received validate email request for email {} for company {}", validateEmailRequest.getEmail(),
//				companyId);
//		validateEmailRequest.validate();
//		CompanyUserDTO companyUserDTO = companyService.validateCompanyUserEmail(companyId,
//				validateEmailRequest.getEmail());
//		logger.info("Validate email response for user {} and companyId {}: {}", validateEmailRequest.getEmail(),
//				companyId, companyUserDTO);
//		return Response.getSuccessResponse(companyUserDTO);
//	}

	/**
	 * Adds a new user to a company
	 * @param companyId - Company Id
	 * @param subscriptionId - Subscription Id
	 * @param addCompanyUserRequest - User profile and licenses association
	 * @return - Added user details along with licenses
	 */
//	@PostMapping(CompanyApiUrl.ADD_COMPANY_USER)
//	public Response<AddCompanyUserDTO> addCompanyUser(@PathVariable long companyId,
//																										@PathVariable long subscriptionId, @RequestBody CompanyUserRequestDTO addCompanyUserRequest) {
//
//		logger.info("Received add company user request for email {} for company {}",
//				addCompanyUserRequest.getUserProfile().getEmail(), companyId);
//		addCompanyUserRequest.validate();
//		AddCompanyUserDTO addCompanyUserDTO = companyService.addCompanyUser(companyId, subscriptionId,
//				addCompanyUserRequest.getUserProfile(), addCompanyUserRequest.getLicenses());
//		logger.info("Add company user response for {} and companyId {}: {}", addCompanyUserRequest.getUserProfile().getEmail(), companyId,
//				addCompanyUserDTO);
//		return Response.getSuccessResponse(addCompanyUserDTO);
//	}

}
