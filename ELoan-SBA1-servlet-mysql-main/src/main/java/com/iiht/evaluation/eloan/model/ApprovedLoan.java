package com.iiht.evaluation.eloan.model;

import java.util.Date;

public class ApprovedLoan {
	String applno;
	int amotsanctioned;
	double interest;
	int loanterm;
	Date psd;
	Date lcd;
	int emi;
	
	public ApprovedLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApprovedLoan(String applno, int amotsanctioned, double interest, int loanterm, Date psd, Date lcd,
			int emi) {
		super();
		this.applno = applno;
		this.amotsanctioned = amotsanctioned;
		this.interest = interest;
		this.loanterm = loanterm;
		this.psd = psd;
		this.lcd = lcd;
		this.emi = emi;
	}

	public String getApplno() {
		return applno;
	}

	public void setApplno(String applno) {
		this.applno = applno;
	}

	public int getAmotsanctioned() {
		return amotsanctioned;
	}

	public void setAmotsanctioned(int amotsanctioned) {
		this.amotsanctioned = amotsanctioned;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getLoanterm() {
		return loanterm;
	}

	public void setLoanterm(int loanterm) {
		this.loanterm = loanterm;
	}

	public Date getPsd() {
		return psd;
	}

	public void setPsd(Date psd) {
		this.psd = psd;
	}

	public Date getLcd() {
		return lcd;
	}

	public void setLcd(Date lcd) {
		this.lcd = lcd;
	}

	public int getEmi() {
		return emi;
	}

	public void setEmi(int emi) {
		this.emi = emi;
	}

	
}
