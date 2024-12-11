package com.training.restLibrary.security.service;

import java.util.List;

import com.training.restLibrary.security.entity.User;

public interface UserService {
	void saveUser(User user);
	
	User updateUser(User user);
	
	User findUserByUserId(String user); 
	
	List<User> getAllUsers();
	
	
}
