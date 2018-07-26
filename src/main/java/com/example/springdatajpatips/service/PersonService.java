package com.example.springdatajpatips.service;

import com.example.springdatajpatips.bean.PersonWithPhone;

import java.util.List;

public interface  PersonService {
	List<PersonWithPhone> getPhoneBooksByPersonName();
}
