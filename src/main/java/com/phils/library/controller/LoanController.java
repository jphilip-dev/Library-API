package com.phils.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phils.library.dto.LoanDTO;
import com.phils.library.service.LoanService;

@RestController
@RequestMapping("/api")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	@GetMapping("/loans")
	public List<LoanDTO> getAllLoans(){
		return loanService.findAllLoans();
	}
	
	@GetMapping("/loans/{id}")
	public LoanDTO getAllLoans(@PathVariable int id){
		return loanService.findLoanById(id);
	}
	
	@PostMapping("/loans")
	public LoanDTO addLoan(@RequestBody LoanDTO loanDTO,Authentication authentication) {
		loanDTO.setUsername(authentication.getName());
		return loanService.addLoan(loanDTO);
	}
	
	@PutMapping("/loans/return/{id}")
	public LoanDTO returnLoan(@PathVariable int id,Authentication authentication) {
		return loanService.returnLoan(id,authentication.getName() );
	}
	
	@DeleteMapping("/loans/{id}")
	public ResponseEntity<Object> deleteLoan(@PathVariable int id){
		loanService.deleteLoan(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
