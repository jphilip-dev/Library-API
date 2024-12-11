package com.training.restLibrary.service;

import java.util.List;

import com.training.restLibrary.entity.Book;

public interface BookService {
	List<Book> findAllBooks();

	Book findBookById(int id);

	Book saveBook(Book book);

	void deleteBook(int id);
}
