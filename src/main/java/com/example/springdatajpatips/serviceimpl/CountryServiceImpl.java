package com.example.springdatajpatips.serviceimpl;

import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;
import com.example.springdatajpatips.model.Country;
import com.example.springdatajpatips.repository.CountryRepository;
import com.example.springdatajpatips.response.IdNameDTO;
import com.example.springdatajpatips.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository countryRepository;

	public Country getCountryById(Long id) {
		Country country = countryRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.INVALID_COUNTRY));
		return country;
	}

	public List<IdNameDTO> list(){
		List<Country> countries = countryRepository.findAllByOrderByOnTopDescNameAsc();
		return countries.stream().map( c -> new IdNameDTO(c.getId(),c.getName())).collect(Collectors.toList());
	}
}
