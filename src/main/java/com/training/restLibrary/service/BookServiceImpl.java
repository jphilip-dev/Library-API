package com.training.restLibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.restLibrary.dao.BookDAO;
import com.training.restLibrary.entity.Book;
import com.training.restLibrary.exception.BookNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService{
	BookDAO bookDAO;

	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public List<Book> findAllBooks() {
		return bookDAO.findAllBooks();
	}

	@Override
	public Book findBookById(int id) {
		return validateBook(id,"");
	}

	@Override
	@Transactional
	public Book saveBook(Book book) {
		if (book == null) {
		    throw new BookNotFoundException("The Book cannot be null");
		}
		
		// if book not new, (update)
		if (book.getId() != 0) {
			validateBook(book.getId(), "while trying to Update");
		}
		
		return bookDAO.saveBook(book);
	}

	@Override
	@Transactional
	public void deleteBook(int id) {
		validateBook(id, "while trying to delete");
		bookDAO.deleteBook(id);
	}
	
	public Book validateBook(int id, String message) {
		Book book = bookDAO.findBookById(id);
		if ( book == null) {
			bookNotFound(id,message);
		}
			return book;
	}
	
	public Throwable bookNotFound(int id, String message) {
	    throw new BookNotFoundException(String.format("Book id (%d) not found %s", id, message));
	}
}
