package com.phils.library.dto;

import java.time.LocalDate;

import com.phils.library.entity.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;


@Data
public class BookDTO {
	private Integer id;
	@NotBlank(message = "Title cannot be blank")
    private String title;
	
	@NotBlank(message = "Author cannot be blank")
    private String author;
	
	@NotBlank(message = "Genre cannot be blank")
    private String genre;
    
    @PastOrPresent(message = "Published date must be in the past or present")
    private LocalDate publishedDate;
    
    public static BookDTO fromEntity(Book book) {
    	BookDTO bookDTO = new BookDTO();
    	bookDTO.setId(book.getId());
    	bookDTO.setTitle(book.getTitle());
    	bookDTO.setAuthor(book.getAuthor());
    	bookDTO.setGenre(book.getGenre());
    	bookDTO.setPublishedDate(book.getPublishedDate());
    	
    	return bookDTO;
    }
    
    public static Book toEntity(BookDTO bookDto) {
    	Book book = new Book();
    	//book.setId(bookDto.getId());
    	book.setTitle(bookDto.getTitle());
    	book.setAuthor(bookDto.getAuthor());
    	book.setGenre(bookDto.getGenre());
    	book.setPublishedDate(bookDto.getPublishedDate());
    	
    	return book;
    }
}
