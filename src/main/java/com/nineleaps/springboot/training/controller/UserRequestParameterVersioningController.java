package com.nineleaps.springboot.training.controller;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.springboot.training.dtos.UserDtoV1;
import com.nineleaps.springboot.training.dtos.UserDtoV2;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@Validated
@RequestMapping(path = "/user/versioning/param")
public class UserRequestParameterVersioningController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
		// Request Parameter base version -V1
		@GetMapping(value = "{id}", params = "version=1")
		public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			try {
				User user = userService.getUserById(id);
				UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
				return userDtoV1;
			} catch (UserNotFoundException ex) {
				throw new UserNotFoundException("User Not Found UserModelMapperController");
			}
		}

		// Request Parameter base version -V2
		@GetMapping(value = "{id}", params = "version=2")
		public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

			try {
				User user = userService.getUserById(id);
				UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
				return userDtoV2;
			} catch (UserNotFoundException ex) {
				throw new UserNotFoundException("User Not Found UserModelMapperController");
			}
		}
}
