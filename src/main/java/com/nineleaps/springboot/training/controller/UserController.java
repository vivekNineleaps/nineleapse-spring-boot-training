package com.nineleaps.springboot.training.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nineleaps.springboot.training.exception.UserExistsException;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.exception.UsernameNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value= {"/create/user"})
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user){
		try {
		userService.createUser(user);
		HttpHeaders headers= new HttpHeaders();
		 URI location = ServletUriComponentsBuilder
                 .fromCurrentContextPath().path("/user/byuserid/{id}")
                 .buildAndExpand(user.getUserid()).toUri();
		headers.setLocation(location);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.FOUND,ex.getMessage());
		}
	}
	@GetMapping(path= "/user/byuserid/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id){
		User user;
		try {
		user= userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		return user;
	}
	
	@GetMapping(path= "/user/byusername/{username}")
	public User getUserById(@PathVariable("username")String username) throws UsernameNotFoundException{
		
		User user= userService.getUserByName(username);
		if(user ==null)
			throw new UsernameNotFoundException("Username not found exception: "+username);
		else
		   return user;
	}
	
	@GetMapping(path= "/users")
	public ResponseEntity<List<User>> getAllUsers(){
		try {
		List<User> usersList=userService.getAllUsers();
		return new ResponseEntity<List<User>>(usersList,HttpStatus.FOUND);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@PutMapping(path= "/update/user/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id")Long id,@RequestBody User user){
		User presentUser;
		try {
			presentUser=userService.updateUserById(id,user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		return new ResponseEntity<User>(presentUser,HttpStatus.OK);
	}
	
	@DeleteMapping(path= "/delete/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable ("id") Long id){
		userService.deleteUserById(id);
		return new ResponseEntity<String>("User Deleted Successfully.",HttpStatus.OK);
	}
	
}
