package com.example.springdatajpatips.service;



import com.example.springdatajpatips.model.Country;
import com.example.springdatajpatips.response.IdNameDTO;

import java.util.List;


public interface CountryService {
	Country getCountryById(Long id);

	List<IdNameDTO> list();
}
