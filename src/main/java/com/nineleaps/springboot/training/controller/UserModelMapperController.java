package com.nineleaps.springboot.training.controller;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.springboot.training.dtos.UserMmDto;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@Validated
@RequestMapping(path="/modelmapper")
public class UserModelMapperController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path= "/user/{id}")
	public UserMmDto getUserByIdExternal(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
		try {
		User user= userService.getUserById(id);
		UserMmDto userDto = modelMapper.map(user,UserMmDto.class);
		return userDto;
		}catch(UserNotFoundException ex) {
			throw new UserNotFoundException("User Not Found UserModelMapperController");
		}
	}
}
