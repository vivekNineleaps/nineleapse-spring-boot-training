package com.nineleaps.springboot.training.service;

import java.util.List;

import com.nineleaps.springboot.training.model.User;

public interface UserService {
	
	void createUser(User user);
	User getUserById(Long id);
	void UpdateUserById(Long id,User user);
	void DeleteUserById(Long id);
	List<User> getAllUsers();
}
