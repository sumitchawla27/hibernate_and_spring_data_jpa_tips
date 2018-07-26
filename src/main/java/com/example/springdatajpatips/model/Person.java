package com.example.springdatajpatips.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Person extends BaseEntity{

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
 }
