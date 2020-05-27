package com.nineleaps.springboot.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value= {"/create/user"})
	public ResponseEntity<String> createUser(@RequestBody User user){
		userService.createUser(user);
		return new ResponseEntity<String>("User Created",HttpStatus.ACCEPTED);
	}
	@GetMapping(path= "/user/byuserid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id")Long id){
		User user=userService.getUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.FOUND);
	}
	@GetMapping(path= "/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> usersList=userService.getAllUsers();
		return new ResponseEntity<List<User>>(usersList,HttpStatus.FOUND);
	}
	
	@PutMapping(path= "/update/user/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id")Long id,@RequestBody User user){
		userService.UpdateUserById(id,user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@DeleteMapping(path= "/delete/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable ("id") Long id){
		userService.DeleteUserById(id);
		return new ResponseEntity<String>("User Deleted Successfully.",HttpStatus.OK);
	}
	
}
