package com.phils.library.service;

import java.util.List;

import com.phils.library.dto.BookDTO;
import com.phils.library.entity.Book;

public interface BookService {
	//Bsic Crud
	
	List<BookDTO> findAllBooks();
	BookDTO findBookById(Integer bookId);
	BookDTO addBook(BookDTO bookDTO);
	BookDTO updateBookById(Integer id, BookDTO bookDTO);
	void deleteBookById(Integer id);
}
