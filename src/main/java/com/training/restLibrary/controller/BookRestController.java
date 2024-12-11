package com.training.restLibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.restLibrary.entity.Book;
import com.training.restLibrary.service.BookService;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class BookRestController {
	
	BookService bookService;
	
	@Autowired
	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostConstruct
	public void loadData() {
		System.out.println("This is called one time when this bean created");
	}
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello Fel, I love you <3";
	}
	
	// get all books
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookService.findAllBooks();
	}
	
	// get book by ID
	@GetMapping("/books/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
		return bookService.findBookById(bookId);
	}
	
	// create new Book
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		book.setId(0); // set to 0 to force new item instead of update since merge is used instead of persist
		return bookService.saveBook(book);
	}
	
	// update Book
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	// delete Book by ID
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		bookService.deleteBook(bookId);
		
		return "Deleted Book ID: " + bookId;
	}
	
}
