package com.nineleaps.springboot.training.dao;

import java.util.List;

import com.nineleaps.springboot.training.model.User;

public interface UserDAO {

	void createUser(User user);
	User getUserById(Long id);
	User getUserByName(String username);
	User updateUserById(Long id,User user);
	void deleteUserById(Long id);
	List<User> getAllUsers();
}
