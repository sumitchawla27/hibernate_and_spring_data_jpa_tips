package com.example.springdatajpatips.repository;

import com.example.springdatajpatips.bean.PersonWithPhone;
import com.example.springdatajpatips.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


	//1
	@Query(value = "SELECT new com.example.springdatajpatips.bean.PersonWithPhone(p.firstName, p.lastName, n.phoneNumber) FROM Person p, PhoneBookEntry n WHERE p.firstName = n.firstName AND p.lastName = n.lastName")
	List<PersonWithPhone> getPhoneBooksByPersonName();

	//2
//	@Query("SELECT new com.example.springdatajpatips.bean.PersonWithPhone(p.firstName, p.lastName, n.phoneNumber) FROM Person p JOIN PhoneBookEntry n ON p.firstName = n.firstName AND p.lastName = n.lastName")
//	List<PersonWithPhone> getPhoneBooksByPersonName();

	//3, with outer join
//	@Query("SELECT new com.example.springdatajpatips.bean.PersonWithPhone(p.firstName, p.lastName, n.phoneNumber) FROM Person p LEFT JOIN PhoneBookEntry n ON p.firstName = n.firstName AND p.lastName = n.lastName")
//	List<PersonWithPhone> getPhoneBooksByPersonName();
}
