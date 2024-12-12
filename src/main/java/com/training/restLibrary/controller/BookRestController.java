package com.training.restLibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.restLibrary.entity.Book;
import com.training.restLibrary.security.entity.User;
import com.training.restLibrary.security.service.UserService;
import com.training.restLibrary.service.BookService;
import com.training.restLibrary.utils.BcryptUtil;
import com.training.restLibrary.utils.ResponseFormat;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class BookRestController {
	
	BookService bookService;
	UserService userService;
	
	@Autowired
	public BookRestController(BookService bookService,UserService userService) {
		this.bookService = bookService;
		this.userService = userService;
	}
	
	@PostConstruct
	public void loadData() {
		System.out.println("This is called one time when this bean created");
	}
	

	// Exposed end point (Permits all)
	@GetMapping()
	public ResponseEntity<ResponseFormat> landingEndpoint(){
		
		String message = "Hello! You're in the landing End point of the Library API!";
	
		return ResponseFormat.buildResponse(HttpStatus.OK, message);
	}
	
	// Registration end point only accessible for unauthenticated users
	@PostMapping("/register")
	public ResponseEntity<ResponseFormat> addUser(@RequestBody User user) {
		//validate user fields -- placeholder
		
		user.setActive(false); // set to false. Administrator will toggle this 
		user.setPassword("{bcrypt}"+BcryptUtil.encodeText(user.getPassword()));
		
		userService.saveUser(user);
		
		return ResponseFormat.buildResponse(HttpStatus.OK, "Registration completed, awaiting Administrator apparoval\n\nThank you");
	}
	
	
	// get all books (User)
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookService.findAllBooks();
	}
	
	// get book by ID (User)
	@GetMapping("/books/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
		return bookService.findBookById(bookId);
	}
	
	
	// create new Book (Administrator)
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		book.setId(0); // set to 0 to force new item instead of update since merge is used instead of persist
		return bookService.saveBook(book);
	}
	
	// update Book (Administrator)
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	// delete Book by ID (Administrator)
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<ResponseFormat> deleteBook(@PathVariable int bookId) {
		bookService.deleteBook(bookId);
		
		return ResponseFormat.buildResponse(HttpStatus.OK, String.format("Book ID (%d) has been deleted.", bookId));
	}
	
	// get ALL users (Administrator)
	@GetMapping({"/users","/users/"}) // reminder for me that i can use multiple end points here
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	// Update user details
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		System.out.println("In the update user");
		return userService.updateUser(user);
	}
	
	// get specific user by Id (Administrator)
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable String userId){
		return userService.findUserByUserId(userId);
	}
	
	
	
}
