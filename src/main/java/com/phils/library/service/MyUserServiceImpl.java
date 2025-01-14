package com.phils.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phils.library.entity.MyUser;
import com.phils.library.entity.UserRole;
import com.phils.library.exception.UsernameAlreadyExistExeption;
import com.phils.library.repository.MyUserRepository;

@Service
public class MyUserServiceImpl implements  MyUserService {
	
	@Autowired
    private MyUserRepository myUserRepository;
	
	@Override
	public List<MyUser> findAllUsers() {
		return myUserRepository.findAll();
	}

	@Override
	public MyUser findUserById(int userId) {
		return myUserRepository.findById(userId)
					.orElseThrow(() -> new IllegalArgumentException()); // placeholder
	}

	@Override
	public MyUser addUser(MyUser user) {
		user.setId(null); // explicitly set to null 
		user.setStatus(false);
		user.setRoles(null);
		
		MyUser checkUser = myUserRepository.findByUsernameWithRoles(user.getUsername()).orElse(null);
		if (checkUser != null ) {
			throw new UsernameAlreadyExistExeption("Username already exists");
		}
		user = myUserRepository.save(user);
		user.addRole("USER");
		user = myUserRepository.save(user);
		return user;
	}

	@Override
	public MyUser updateUser(int userId, MyUser user) {
		user.setId(userId);
		return myUserRepository.save(user);
	}

	@Override
	public void deleteUser(int userId) {
		
		// check if user exist
		MyUser user = myUserRepository.findById(userId)
					.orElseThrow(() -> new IllegalArgumentException("Delete error: id not Found"));// placeholder
		
		myUserRepository.delete(user);

	}

	@Override
	public MyUser findUserByUsername(String username) {
		return myUserRepository.findByUsernameWithRoles(username)
					.orElseThrow(() -> new IllegalArgumentException());// placeholder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser myUser = findUserByUsername(username);
		
		return User.builder()
				.username(username)
				.password(myUser.getPassword())
				.roles(getRoles(myUser.getRoles()))
				.disabled(!myUser.getStatus())
				.build();
		
	}
	
	private String[] getRoles(List<UserRole> roles) {
	    return roles.stream()
	                  .map(r -> r.getRole()) // Lambda used here
	                  .toArray(String[]::new); // Convert to a String array
	}
}
