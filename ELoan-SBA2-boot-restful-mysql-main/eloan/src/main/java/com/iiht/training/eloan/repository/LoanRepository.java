package com.iiht.training.eloan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.Users;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	//Loan findAllBycustomerId(Long CustomerId);
	Loan findAllByloanApplicationNumber(Long loanApplicationNumber);
	List<Loan> findAllBycustomerId(Long CustomerId);
	//Loan findAllBycustomerId(Long CustomerId);
	List<Loan> findAllByStatus(Integer Status);
	

}
