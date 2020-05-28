package com.nineleaps.springboot.training.dtos;

import lombok.Data;

public @Data class UserMmDto {

	private Long userid;
	private String username;
	private String firstName;
	private String lastName;
	//private String emailId; -- Every fields must match to the User model class fields otherwise it gives null for unmatched field.
	private String email;
}
