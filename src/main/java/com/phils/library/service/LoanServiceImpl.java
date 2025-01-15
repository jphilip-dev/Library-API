package com.phils.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phils.library.dto.LoanDTO;
import com.phils.library.entity.Book;
import com.phils.library.entity.Loan;
import com.phils.library.entity.MyUser;
import com.phils.library.exception.BookNotFoundException;
import com.phils.library.exception.LoanException;
import com.phils.library.repository.BookRepository;
import com.phils.library.repository.LoanRepository;
import com.phils.library.repository.MyUserRepository;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	MyUserRepository myUserRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<LoanDTO> findAllLoans() {
		
		return loanRepository.findAll().stream()
							.map(loan -> LoanDTO.fromEntity(loan))
							.collect(Collectors.toList());
	}

	@Override
	public LoanDTO findLoanById(int id) {
		return LoanDTO.fromEntity(loanRepository.findById(id).orElseThrow(() -> new LoanException("Invalid Loan ID")));
	}

	@Override
	public LoanDTO addLoan(LoanDTO loanDTO) {
		Loan loan = new Loan();
		
		MyUser user = myUserRepository.findByUsernameWithRoles(loanDTO.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username " +loanDTO.getUsername() + " do not exists"  ));
		Book book = bookRepository.findByTitle(loanDTO.getBookTitle()).orElseThrow(() -> new BookNotFoundException("Book Not Found"));
		
		book.lend();
		
		loan.setUser(user);
		loan.setBook(book);
		loan.setLoanDate(LocalDate.now());
		loan.setDueDate(LocalDate.now().plusDays(7));
		loan.setReturnedDate(null);
		
		return LoanDTO.fromEntity(loanRepository.save(loan));
	}

	@Override
	public LoanDTO returnLoan(int id,String username) {
		Loan loan =  loanRepository.findById(id).orElseThrow(() -> new LoanException("Invalid Loan ID"));
		
		if(!loan.getUser().getUsername().equals(username)  && !username.equals("admin") ) { // change to role base validation
			throw new LoanException("This Loan cannot be returned by user : " + username );
		}
		
		if(loan.getReturnedDate() != null) {
			throw new LoanException("Loan already returned.." );
		}
		
		
		Book book = loan.getBook();
		book.returnBook();
		
		loan.setBook(book);
		
		if(LocalDate.now().isAfter(loan.getDueDate())) {
			throw new LoanException("Overdue..");
		}
		
		loan.setReturnedDate(LocalDate.now());
		
		return LoanDTO.fromEntity(loanRepository.save(loan));
	}

	@Override
	public void deleteLoan(int id) {
		Loan loan =  loanRepository.findById(id).orElseThrow(() -> new LoanException("Invalid Loan ID"));
		loanRepository.delete(loan);
	}

}
