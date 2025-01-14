package com.phils.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phils.library.entity.MyUser;
import com.phils.library.service.MyUserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	MyUserService myUserService;

	@GetMapping("/users")
	public List<MyUser> getAllUsers() {
		return myUserService.findAllUsers();
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody MyUser myUser) {
		myUserService.addUser(myUser);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("users/{id}")
	public MyUser updateUser(@PathVariable int id, @RequestBody MyUser myUser) {
		return myUserService.updateUser(id, myUser);
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		myUserService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
