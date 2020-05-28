package com.nineleaps.springboot.training.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class UserDtoV2 {
	
	private Long userid;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String ssn;
	private String address;

}
