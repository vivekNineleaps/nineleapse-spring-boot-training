package com.nineleaps.springboot.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nineleaps.springboot.training.dao.UserDAO;
import com.nineleaps.springboot.training.exception.UserExistsException;
import com.nineleaps.springboot.training.exception.UserNotFoundException;
import com.nineleaps.springboot.training.model.User;
import com.nineleaps.springboot.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void createUser(User user) throws UserExistsException{
		
		User isUserExists =userDao.getUserByName(user.getUsername());
		Optional<User> op = Optional.ofNullable(isUserExists);
		if(op.isPresent())
			throw new UserExistsException("User already exists in repository");
		else
		userDao.createUser(user);
	}

	@Override
	public User getUserById(Long id) throws UserNotFoundException {
		User user = userDao.getUserById(id);
		Optional<User> op = Optional.ofNullable(user);
		if (!op.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		return user;
	}
	
	@Override
	public 	User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		List<User> usersList=userDao.getAllUsers();
		Optional<List<User>> op = Optional.ofNullable(usersList);
		if(!op.isPresent())
			throw new UserNotFoundException("No User in the repository");
		else
			return usersList;
		
	}

	@Override
	public User updateUserById(Long id, User user) throws UserNotFoundException {

		User presentUser = userDao.updateUserById(id, user);
		Optional<User> op = Optional.ofNullable(presentUser);
		if (!op.isPresent())
			throw new UserNotFoundException("Can not update the user because it's not found.");
		else
			return presentUser;

	}

	@Override
	public void deleteUserById(Long id) {

		User user = userDao.getUserById(id);
		Optional<User> op = Optional.ofNullable(user);
		if (!op.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
		else
			userDao.deleteUserById(id);
	}

}
