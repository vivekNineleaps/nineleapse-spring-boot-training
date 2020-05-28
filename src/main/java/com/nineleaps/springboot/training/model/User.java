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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description="It's a user model")
@Entity
@Table(name="NINELEAPS_USER")
@NoArgsConstructor
//@JsonIgnoreProperties(value= {"orders","firstName","lastName"}) -- This is for Static Filtering.
@JsonIgnoreProperties(value= {"orders"})
//@JsonFilter(value="userFilter")- This is for MapppingJacksonValue Dynamic Filtering.
public @Data class User {
	
	@ApiModelProperty(notes ="Auto generated unique id", required =true, position = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@JsonView(Views.External.class)
	private Long userid;
	@ApiModelProperty(notes ="User name should be flname", example ="vrathi", required =false, position = 2)
	@NotEmpty(message="Username is mandatory field. Please provide the username")
	@Size(min=2,message="First name should have atleast two character")
	@Column(name="USERNAME",length=50, nullable=false,unique=true)
	@JsonView(Views.External.class)
	private String username;
	@ApiModelProperty(notes ="First Name", example ="Gal", required =false, position = 3)
	@Column(name="FIRSTNAME" ,length=100, nullable=false)
	@JsonView(Views.External.class)
	@Size(min = 3, message="First name should have atleast 3 characters")
	private String firstName;
	@ApiModelProperty(notes ="Last Name", example ="Gadot", required =false, position = 4)
	@Column(name="LASTNAME" ,length=100, nullable=false)
	@JsonView(Views.External.class)
	@Size(min = 3, message="Last name should have atleast 3 characters")
	private String lastName;
	@ApiModelProperty(notes ="Email", example ="vivek.rathi@nineleapse.com", required =false, position = 5)
	@Column(name="EMAIL",length=100, nullable=false,unique=true)
	@JsonView(Views.External.class)
	private String email;
	@ApiModelProperty(notes ="Administrator", example ="Admin", required =false, position = 6)
	@Column(name="ROLE")
	@JsonView(Views.Internal.class)
	private String role;
	@ApiModelProperty(notes ="SSN", example ="1001XYZ", required =true, position = 7)
	@Column(name="SSN",length=100, nullable=false, unique=true)
	//@JsonIgnore -- This is for Static Filtering.
	@JsonView(Views.Internal.class)
	private String ssn;
	@ApiModelProperty(notes ="Address Details", example ="Noida-India", required =false, position = 8)
	@Column(name="ADDRESS")
	private String address;
	@ApiModelProperty(required =false, position = 9)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Order> orders;

}
