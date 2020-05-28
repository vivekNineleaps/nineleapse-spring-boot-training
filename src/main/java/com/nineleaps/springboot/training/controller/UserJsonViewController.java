package com.nineleaps.springboot.training.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.model.Views;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@Validated
@RequestMapping(value="/jsonview")
public class UserJsonViewController {
	
	@Autowired
	private UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping(path= "/user/external/{id}")
	public User getUserByIdExternal(@PathVariable("id") @Min(1) Long id){
		
		try {
		return userService.getUserById(id);
		
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@JsonView(Views.Internal.class)
	@GetMapping(path= "/user/internal/{id}")
	public User getUserByIdInternal(@PathVariable("id") @Min(1) Long id){
		
		try {
		return userService.getUserById(id);
		
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}

}
