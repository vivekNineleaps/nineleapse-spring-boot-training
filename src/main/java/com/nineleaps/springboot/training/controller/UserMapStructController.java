package com.nineleaps.springboot.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.springboot.training.dtos.UserMsDto;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.mapper.UserMapper;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@RequestMapping(value="/mapstruct")
public class UserMapStructController {
	
	@Autowired
	private UserService userService;

	
	/*
	 * @Autowired private UserMapper userMapper;
	 * 
	 * 
	 * @GetMapping(path="/alluser") public List<UserMsDto> getAllUserDtos() throws
	 * UserNotFoundException{ List<UserMsDto> dtos=null; try { dtos=
	 * userMapper.userToUserMsDtos(userService.getAllUsers()); } catch
	 * (UserNotFoundException e) { throw new
	 * UserNotFoundException("User Not Found from UserMapStructController"); }
	 * return dtos; }
	 */
	
}
