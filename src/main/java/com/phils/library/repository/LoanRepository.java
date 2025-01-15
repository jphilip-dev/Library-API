package com.phils.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phils.library.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{

}
