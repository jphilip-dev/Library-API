package com.phils.library.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.phils.library.entity.MyUser;

public interface MyUserService extends UserDetailsService{
	// Basic Crud
	List<MyUser> findAllUsers();
	MyUser findUserById(int userId);
	MyUser addUser(MyUser user);
	MyUser updateUser(int userId, MyUser user);
	void deleteUser(int userId);
	
	//Custom
	MyUser findUserByUsername(String username);
}
