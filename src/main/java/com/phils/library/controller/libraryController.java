package com.phils.library.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class libraryController {
	
	@GetMapping("/books")
	public String getBooks() {
		return "Books";
	}
	
	@GetMapping("/books/{bookId}")
	public String getBookById( @PathVariable int bookId) {
		return "BookById - "+ bookId;
	}
	
	@PostMapping("/books")
	public String addBook(@RequestBody String book) {
		return "Add book - " + book;
	}
	
	@PutMapping("/books/{bookId}")
	public String updateBookById(@PathVariable int bookId, @RequestBody String newDetails) {
		
		return "Update Book - " + bookId + " : " + newDetails;
	}
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBookById(@PathVariable int bookId) {
		return "Delete Book - " + bookId ;
	}
	
}
