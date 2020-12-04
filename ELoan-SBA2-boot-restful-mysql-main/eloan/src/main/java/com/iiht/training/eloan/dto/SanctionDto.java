package com.iiht.training.eloan.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class SanctionDto {
	@NotNull(message="loanAmountSanctioned is mandatory")	
	@Range(min=1,message="loanAmountSanctioned must be greater than 0")
	private Double loanAmountSanctioned;
	
	@NotNull(message="termOfLoan is mandatory")	
	@Range(min=1,message="termOfLoan must be greater than 0")
	private Double termOfLoan;	
	
	private String paymentStartDate;
	
	public Double getLoanAmountSanctioned() {
		return loanAmountSanctioned;
	}
	public void setLoanAmountSanctioned(Double loanAmountSanctioned) {
		this.loanAmountSanctioned = loanAmountSanctioned;
	}
	public Double getTermOfLoan() {
		return termOfLoan;
	}
	public void setTermOfLoan(Double termOfLoan) {
		this.termOfLoan = termOfLoan;
	}
	public String getPaymentStartDate() {
		return paymentStartDate;
	}
	public void setPaymentStartDate(String paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}
	
	
}
