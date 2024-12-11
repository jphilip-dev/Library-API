package com.training.restLibrary.security.dao;

import java.util.List;

import com.training.restLibrary.security.entity.User;

public interface UsersDAO {
	void saveUser(User users);
	
	User updateUser(User user);
	
	User findUserByUserId(String user);
	
	List<User> getAllUsers();
}
