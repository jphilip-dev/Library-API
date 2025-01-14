package com.phils.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phils.library.dto.BookDTO;
import com.phils.library.entity.Book;
import com.phils.library.exception.BookNotFoundException;
import com.phils.library.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<BookDTO> findAllBooks() {
		
		return bookRepository.findAll().stream()
	 			.map(book -> BookDTO.fromEntity(book))
	 			.collect(Collectors.toList());
	}

	@Override
	public BookDTO findBookById(Integer bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Cannot find Book with id: " + bookId)); 
		return BookDTO.fromEntity(book);
	}

	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		
		Book book = BookDTO.toEntity(bookDTO);
		
		book.setId(null);
		
		Book newBook = bookRepository.save(book);
		
		return BookDTO.fromEntity(newBook);
	}

	@Override
	public BookDTO updateBookById(Integer bookId, BookDTO bookDTO) {
		
		Book book = BookDTO.toEntity(bookDTO);
		
		if (!isExisting(bookId)) {
			throw new BookNotFoundException("Cannot update non existing Book id: "+ bookId);
		}
		
		book.setId(bookId);
		
		return BookDTO.fromEntity( bookRepository.save(book));
	}

	@Override
	public void deleteBookById(Integer bookId) {
	    Book book = bookRepository.findById(bookId).orElse(null);

	    if (book == null) {
	        throw new BookNotFoundException("Cannot delete non existing Book id: "+ bookId);
	    }
	    bookRepository.delete(book);
	}
	
	// convenience method
	public boolean isExisting(int id) {
		return bookRepository.findById(id).isPresent();
	}

}
