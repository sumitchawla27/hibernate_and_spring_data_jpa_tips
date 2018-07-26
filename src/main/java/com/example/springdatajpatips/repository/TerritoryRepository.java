package com.example.springdatajpatips.repository;


import com.example.springdatajpatips.model.Territory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory,Long> {
	List<Territory> findAllByOrderByOnTopDescNameAsc();
}
