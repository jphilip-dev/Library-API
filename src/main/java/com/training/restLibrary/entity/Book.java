package com.training.restLibrary.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "publishedDate")
	private LocalDate publishedDate;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "genre")
	private int genre;
	
	@Column(name = "copies_available")
	private int copies_available;
	
	@Column(name = "total_copies")
	private int total_copies;

	// constructors

	public Book() {

	}

	public Book(String title) {
		this.title = title;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public int getCopies_available() {
		return copies_available;
	}

	public void setCopies_available(int copies_available) {
		this.copies_available = copies_available;
	}

	public int getTotal_copies() {
		return total_copies;
	}

	public void setTotal_copies(int total_copies) {
		this.total_copies = total_copies;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}
	
	

}
