package com.phils.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phils.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	Optional<Book> findByTitle(String title);
}
