package com.phils.library.dto;

import java.time.LocalDate;

import com.phils.library.entity.Loan;

import lombok.Data;

@Data
public class LoanDTO {
	private int id;
	private String username;
	private String bookTitle;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	
	public static LoanDTO fromEntity(Loan loan) {
		LoanDTO loanDTO = new LoanDTO();
		
		loanDTO.setId(loan.getId());
		loanDTO.setUsername(loan.getUser().getUsername());
		loanDTO.setBookTitle(loan.getBook().getTitle());
		loanDTO.setLoanDate(loan.getLoanDate());
		loanDTO.setDueDate(loan.getDueDate());
		loanDTO.setReturnedDate(loan.getReturnedDate());
		
		
		return loanDTO;
		
	}
}
