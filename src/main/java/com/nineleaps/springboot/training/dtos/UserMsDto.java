package com.nineleaps.springboot.training.dtos;

import lombok.Data;

public @Data class UserMsDto {
	
	private Long userid;
	private String username;
	private String emailAddress;
	
	public UserMsDto(Long userid, String username, String emailAddress) {
		super();
		this.userid = userid;
		this.username = username;
		this.emailAddress = emailAddress;
	}
	
	

}
