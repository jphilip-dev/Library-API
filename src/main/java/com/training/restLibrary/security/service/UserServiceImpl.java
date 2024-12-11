package com.training.restLibrary.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.restLibrary.exception.UserNotFoundException;
import com.training.restLibrary.security.dao.UsersDAO;
import com.training.restLibrary.security.entity.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	UsersDAO usersDAO;
	
	public UserServiceImpl(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	@Override
	@Transactional
	public void saveUser(User user) {
		usersDAO.saveUser(user);
	}

	@Override
	public User findUserByUserId(String user) {
		User db_user = usersDAO.findUserByUserId(user);
		if (db_user == null) {
			throw new UserNotFoundException(String.format("User ID (%s) not found, Please try again..", user)); // place holder
		}
		return db_user;
	}

	@Override
	public List<User> getAllUsers() {
		return usersDAO.getAllUsers();
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		
		// use the findUserByUserId to check user
		findUserByUserId(user.getUser());
		
		return usersDAO.updateUser(user);
		
	}
	

}
