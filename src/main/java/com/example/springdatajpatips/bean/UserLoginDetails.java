package com.example.springdatajpatips.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDetails {
	
	private Long userId;
	private Date loginTs;
	private Long count;

	public UserLoginDetails(Long userId, Long count) {
		this.userId = userId;
		this.count = count;
	}
}
