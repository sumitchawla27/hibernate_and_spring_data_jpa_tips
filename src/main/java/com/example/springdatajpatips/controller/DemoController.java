package com.example.springdatajpatips.controller;

import com.example.springdatajpatips.bean.PersonWithPhone;
import com.example.springdatajpatips.constants.CommonApiUrl;
import com.example.springdatajpatips.constants.CompanyApiUrl;
import com.example.springdatajpatips.response.CompanyProfileResponse;
import com.example.springdatajpatips.response.Response;
import com.example.springdatajpatips.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonApiUrl.API_VERSION_1)
public class DemoController {

	@Autowired
	PersonService personService;

	@GetMapping("/person")
	public Response<List<PersonWithPhone>> getPhoneBooksByPersonName() {
		return Response.getSuccessResponse(personService.getPhoneBooksByPersonName());
	}

}
