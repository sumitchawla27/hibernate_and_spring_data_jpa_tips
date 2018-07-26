package com.example.springdatajpatips.bean;


import com.example.springdatajpatips.enums.UserLevel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private UserLevel level;
	
}
