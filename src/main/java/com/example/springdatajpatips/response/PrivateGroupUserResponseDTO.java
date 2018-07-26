package com.example.springdatajpatips.response;


import com.example.springdatajpatips.bean.UserProfile;

import lombok.Data;

@Data
public class PrivateGroupUserResponseDTO extends UserProfile {

	private String guid;
	private IdNameDTO territory;

}
