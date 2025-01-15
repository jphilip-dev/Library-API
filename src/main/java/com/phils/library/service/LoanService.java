package com.phils.library.service;

import java.util.List;

import com.phils.library.dto.LoanDTO;
import com.phils.library.entity.Loan;

public interface LoanService {
	List<LoanDTO> findAllLoans();
	LoanDTO findLoanById(int id);
	LoanDTO addLoan(LoanDTO loanDTO);
	LoanDTO returnLoan(int id, String username);
	void deleteLoan(int id);
	
	
}
