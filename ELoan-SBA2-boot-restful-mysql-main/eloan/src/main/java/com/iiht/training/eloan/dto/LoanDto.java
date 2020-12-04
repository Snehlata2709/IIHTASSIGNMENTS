package com.iiht.training.eloan.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class LoanDto {

	@NotNull(message="LoanName is mandatory")	
	@Size(min=3,max=100,message="LoanName must be 3 to 100 chars in length")
	private String loanName;
	
	@NotNull(message="LoanAmount is mandatory")	
	@Range(min=1,message="LoanAmount must be greater than 0")
	private Double loanAmount;
	
	private String loanApplicationDate;	
	
	@Pattern(regexp="Individual|Organizational", message="Business Structure can be only Individual or organizational")
	private String businessStructure;
	
	@Pattern(regexp="Salaried|Non-Salaried", message="Billing Indicator can be only Salaried or Non-salaried")
	private String billingIndicator;
	
	@Pattern(regexp="TaxPayer|Non-TaxPayer", message="Tax Indicator can be only TaxPayer or Non-TaxPayer")
	private String taxIndicator;
	
	@NotNull(message="Email is mandatory")
	@Size(min=3,max=100,message="Email must be 3 to 100 chars in length")
	@Email(message = "Email Id is not Valid")
	private String email;
	
	@NotNull(message="Mobile is mandatory")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile number must be exactly ten digits")	
	private String mobile;
	
	private String Address;
	
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanApplicationDate() {
		return loanApplicationDate;
	}
	public void setLoanApplicationDate(String loanApplicationDate) {
		this.loanApplicationDate = loanApplicationDate;
	}
	public String getBusinessStructure() {
		return businessStructure;
	}
	public void setBusinessStructure(String businessStructure) {
		this.businessStructure = businessStructure;
	}
	public String getBillingIndicator() {
		return billingIndicator;
	}
	public void setBillingIndicator(String billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	public String getTaxIndicator() {
		return taxIndicator;
	}
	public void setTaxIndicator(String taxIndicator) {
		this.taxIndicator = taxIndicator;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	
}
