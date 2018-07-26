package com.example.springdatajpatips.repository;

import com.example.springdatajpatips.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
	
	List<Country> findAllByOrderByOnTopDescNameAsc();

	Country findByCode(String code);
}
