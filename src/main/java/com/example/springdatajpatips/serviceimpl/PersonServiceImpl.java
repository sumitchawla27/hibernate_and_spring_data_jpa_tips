package com.example.springdatajpatips.serviceimpl;

import com.example.springdatajpatips.bean.PersonWithPhone;
import com.example.springdatajpatips.controller.CompanyController;
import com.example.springdatajpatips.repository.PersonRepository;
import com.example.springdatajpatips.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private  EntityManager em;

	@Autowired
	private PersonRepository personRepository;

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	public List<PersonWithPhone> getPhoneBooksByPersonName() {

		List<PersonWithPhone> results = personRepository.getPhoneBooksByPersonName();
		for (PersonWithPhone person : results) {
			logger.info(person.getFirstName() + " - "+ person.getLastName() + " - " + person.getPhone());
		}
		return results;
	}
}
