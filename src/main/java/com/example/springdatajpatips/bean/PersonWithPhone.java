package com.example.springdatajpatips.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonWithPhone {
	private String firstName;
	private String lastName;
	private String phone;
}
