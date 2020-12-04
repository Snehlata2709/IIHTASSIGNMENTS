package com.iiht.training.eloan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) throws ExceptionResponse{
		return new ResponseEntity<>(customerService.register(userDto),HttpStatus.OK);
	}
	
	@PostMapping("/apply-loan/{customerId}")
	public ResponseEntity<LoanOutputDto> applyLoan(@PathVariable Long customerId,
			@Valid @RequestBody LoanDto loanDto)throws ExceptionResponse {
		return new ResponseEntity<>(customerService.applyLoan(customerId,loanDto),HttpStatus.OK);
	}
	
	@GetMapping("/loan-status/{loanAppId}")			
	public ResponseEntity<LoanOutputDto> getStatus(@PathVariable("loanAppId") Long loanAppId) throws ExceptionResponse{
		//return null;		
				return new ResponseEntity<>(customerService.getStatus(loanAppId),HttpStatus.OK);
	}
	
	@GetMapping("/loan-status-all/{customerId}")
	public ResponseEntity<List<LoanOutputDto>> getStatusAll(@PathVariable Long customerId) throws ExceptionResponse{
		//return null;
		return new ResponseEntity<>(customerService.getStatusAll(customerId),HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(CustomerNotFoundException ex){
		ExceptionResponse exception = 
				new ExceptionResponse(ex.getMessage(),
									  System.currentTimeMillis(),
									  HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response =
				new ResponseEntity<ExceptionResponse>(exception, HttpStatus.NOT_FOUND);
		return response;
	}
}
