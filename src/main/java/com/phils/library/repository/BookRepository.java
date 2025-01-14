package com.phils.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phils.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
