package com.nineleaps.springboot.training.service;

import java.util.List;

import com.nineleaps.springboot.training.exception.UserExistsException;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;

public interface UserService {
	
	void createUser(User user) throws UserExistsException;
	User getUserById(Long id) throws UserNotFoundException;
	User getUserByName(String username);
	User updateUserById(Long id,User user) throws UserNotFoundException;
	void deleteUserById(Long id);
	List<User> getAllUsers() throws UserNotFoundException;
}
