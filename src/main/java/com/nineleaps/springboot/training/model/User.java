package com.nineleaps.springboot.training.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NINELEAPS_USER")
@NoArgsConstructor
//@JsonIgnoreProperties(value= {"orders","firstName","lastName"}) -- This is for Static Filtering.
@JsonIgnoreProperties(value= {"orders"})
//@JsonFilter(value="userFilter")- This is for MapppingJacksonValue Dynamic Filtering.
public @Data class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@JsonView(Views.External.class)
	private Long userid;
	@NotEmpty(message="Username is mandatory field. Please provide the username")
	@Size(min=2,message="First name should have atleast two character")
	@Column(name="USERNAME",length=50, nullable=false,unique=true)
	@JsonView(Views.External.class)
	private String username;
	@Column(name="FIRSTNAME" ,length=100, nullable=false)
	@JsonView(Views.External.class)
	private String firstName;
	@Column(name="LASTNAME" ,length=100, nullable=false)
	@JsonView(Views.External.class)
	private String lastName;
	@Column(name="EMAIL",length=100, nullable=false,unique=true)
	@JsonView(Views.External.class)
	private String email;
	@Column(name="ROLE")
	@JsonView(Views.Internal.class)
	private String role;
	@Column(name="SSN",length=100, nullable=false, unique=true)
	//@JsonIgnore -- This is for Static Filtering.
	@JsonView(Views.Internal.class)
	private String ssn;
	@Column(name="ADDRESS")
	private String address;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Order> orders;

}
