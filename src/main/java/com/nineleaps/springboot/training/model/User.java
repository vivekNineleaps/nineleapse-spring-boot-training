package com.nineleaps.springboot.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="NINELEAPS_USER")
public @Data class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long user_id;
	@Column(name="USERNAME",length=50, nullable=false,unique=true)
	private String username;
	@Column(name="FIRSTNAME" ,length=100, nullable=false)
	private String firstName;
	@Column(name="LASTNAME" ,length=100, nullable=false)
	private String lastName;
	@Column(name="EMAIL",length=100, nullable=false,unique=true)
	private String email;
	@Column(name="ROLE")
	private String role;
	@Column(name="SSN",length=100, nullable=false,unique=true)
	private String ssn;

}
