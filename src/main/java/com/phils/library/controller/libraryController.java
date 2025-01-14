package com.phils.library.controller;

import org.springframework.web.bind.annotation.RestController;

import com.phils.library.dto.BookDTO;
import com.phils.library.service.BookService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class LibraryController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public List<BookDTO> getBooks() {
		return bookService.findAllBooks();
	}
	
	@GetMapping("/books/{bookId}")
	public BookDTO getBookById(@PathVariable int bookId) {
		return bookService.findBookById(bookId);
	}
	
	@PostMapping("/books")
	public BookDTO addBook(@Valid @RequestBody  BookDTO BookDTO) {
		return bookService.addBook(BookDTO);
	}
	
	@PutMapping("/books/{bookId}")
	public BookDTO updateBookById(@PathVariable int bookId, @RequestBody BookDTO bookDTO) {
		
		return bookService.updateBookById(bookId, bookDTO);
	}
	
	@DeleteMapping("/books/{bookId}")
	public void deleteBookById(@PathVariable int bookId) {
		bookService.deleteBookById(bookId);
	}
	
}
