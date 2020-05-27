package com.nineleaps.springboot.training.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nineleaps.springboot.training.dao.UserDAO;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;

	@Override
	public void createUser(User user) {
		userDao.createUser(user);		
	}

	@Override
	public User getUserById(Long id) {
		User user =userDao.getUserById(id);
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void UpdateUserById(Long id,User user) {
		 userDao.UpdateUserById(id,user);		
	}

	@Override
	public void DeleteUserById(Long id) {
		userDao.DeleteUserById(id);		
	}


}
