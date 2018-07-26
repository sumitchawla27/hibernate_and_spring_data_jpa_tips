package com.example.springdatajpatips.repository;

import com.example.springdatajpatips.enums.Status;
import com.example.springdatajpatips.model.Company;

import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	//Company findByName(String name);


	//1
	Optional<Company> findByIdAndStatus(Long companyId, Status status);

	//2
	Optional<Company> findById(Long companyId);

	//3
//	@EntityGraph(value = "graph.countryAndTerritory", type = EntityGraph.EntityGraphType.FETCH)
//	@Query("select c from Company c where c.id=:companyId")
//	Optional<Company> findById(@Param("companyId") Long companyId);

	//4
	//@EntityGraph(value = "graph", attributePaths = {"country","territory"})
	//@Query("select c from Company c where c.id=:companyId")
	//Optional<Company> findById(@Param("companyId") Long companyId);


	//5
	//	@Query("select c from Company c join fetch c.country join fetch c.territory where c.id=:companyId")
	//	Optional<Company> findById(@Param("companyId") Long companyId);


	@EntityGraph(value = "graph.countryAndTerritory", type = EntityGraph.EntityGraphType.FETCH)
	Page<Company> findAll(Pageable pageable);

//	@Modifying
//	@Query("UPDATE Company c SET c.status = 0 WHERE c.id IN :ids")
//	void deleteByIds(@Param("ids") List<Long> ids);



}
