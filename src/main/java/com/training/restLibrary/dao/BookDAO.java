package com.training.restLibrary.dao;

import java.util.List;

import com.training.restLibrary.entity.Book;

public interface BookDAO {
	
	List<Book> findAllBooks();
	
	Book findBookById(int id);
	
	Book saveBook(Book book);
	
	void deleteBook(int id);

}
