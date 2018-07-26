package com.example.springdatajpatips.serviceimpl;


import com.example.springdatajpatips.exception.CustomError;
import com.example.springdatajpatips.exception.CustomException;
import com.example.springdatajpatips.model.Territory;
import com.example.springdatajpatips.repository.TerritoryRepository;
import com.example.springdatajpatips.response.IdNameDTO;
import com.example.springdatajpatips.service.TerritoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerritoryServiceImpl implements TerritoryService {

	@Autowired
	TerritoryRepository territoryRepository;

	@Override
	public Territory getTerritoryById(Long id) {
		return territoryRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.INVALID_TERRITORY));
	}

	@Override
	public List<IdNameDTO> list() {
		List<Territory> territories = territoryRepository.findAllByOrderByOnTopDescNameAsc();
		return territories.stream().map( c -> new IdNameDTO(c.getId(),c.getName())).collect(Collectors.toList());
	}
}
