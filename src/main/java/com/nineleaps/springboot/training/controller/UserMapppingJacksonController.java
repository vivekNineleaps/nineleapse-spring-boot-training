package com.nineleaps.springboot.training.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@Validated
@RequestMapping(value="/jacksonfilter")
public class UserMapppingJacksonController {
	
	@Autowired
	private UserService userService;
	
	//Filter by Set 
	@GetMapping(path= "/user/byuserid/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id){
		
		try {
		User user= userService.getUserById(id);
		Set<String> fields = new HashSet<String>();
		fields.add("userid");
		fields.add("username");
		fields.add("ssn");
		fields.add("firstName");
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@GetMapping(path= "/user/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,@RequestParam Set<String> fields){
		
		try {
		User user= userService.getUserById(id);
				FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		MappingJacksonValue mapper = new MappingJacksonValue(user);
		mapper.setFilters(filterProvider);
		return mapper;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}

}
