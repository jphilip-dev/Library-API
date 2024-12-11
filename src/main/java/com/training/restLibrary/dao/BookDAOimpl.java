package com.training.restLibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.restLibrary.entity.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class BookDAOimpl implements BookDAO{
	
	EntityManager entityManager;
	
	@Autowired
	public BookDAOimpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Book> findAllBooks() {
		TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
		return query.getResultList();
	}

	@Override
	public Book findBookById(int id) {
		return entityManager.find(Book.class, id);
	}

	@Override
	public Book saveBook(Book book) {
		return entityManager.merge(book);
	}

	@Override
	public void deleteBook(int id) {
		entityManager.remove(entityManager.find(Book.class, id));
	}
	
	

}
