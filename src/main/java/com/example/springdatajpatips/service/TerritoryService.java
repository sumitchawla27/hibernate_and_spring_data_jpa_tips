package com.example.springdatajpatips.service;


import com.example.springdatajpatips.model.Territory;
import com.example.springdatajpatips.response.IdNameDTO;

import java.util.List;

public interface TerritoryService {

	Territory getTerritoryById(Long id);

	List<IdNameDTO> list();
}
