package com.phils.library.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(
	    name = "loans",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "book_id","loan_date"}))
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	@Column(name = "loan_date",nullable = false)
	private LocalDate loanDate;
	
	@Column(name = "due_date",nullable = false)
	private LocalDate dueDate;
	
	@Column(name = "returned_date" )
	private LocalDate returnedDate;
	
}
