package com.iiht.training.eloan.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class ProcessingDto {
	@NotNull(message="AcresOfLoan is mandatory")	
	@Range(min=1,message="AcresOfLand must be greater than 0")
	private Double acresOfLand;
	
	@NotNull(message="LandValue is mandatory")	
	@Range(min=1,message="Landvalue must be greater than 0")
	private Double landValue;
	
	private String appraisedBy;
	private String valuationDate;
	
	@NotNull(message="addressOfProperty is mandatory")	
	@Size(min=3,max=150,message="addressOfProperty must be greater than 3 and lesser than 150")
	private String addressOfProperty;
	
	@NotNull(message="suggestedAmountOfLoan is mandatory")	
	@Range(min=1,message="suggestedAmountOfLoan must be greater than 0")
	private Double suggestedAmountOfLoan;
	
	public Double getAcresOfLand() {
		return acresOfLand;
	}
	public void setAcresOfLand(Double acresOfLand) {
		this.acresOfLand = acresOfLand;
	}
	public Double getLandValue() {
		return landValue;
	}
	public void setLandValue(Double landValue) {
		this.landValue = landValue;
	}
	public String getAppraisedBy() {
		return appraisedBy;
	}
	public void setAppraisedBy(String appraisedBy) {
		this.appraisedBy = appraisedBy;
	}
	public String getValuationDate() {
		return valuationDate;
	}
	public void setValuationDate(String valuationDate) {
		this.valuationDate = valuationDate;
	}
	public String getAddressOfProperty() {
		return addressOfProperty;
	}
	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
	}
	public Double getSuggestedAmountOfLoan() {
		return suggestedAmountOfLoan;
	}
	public void setSuggestedAmountOfLoan(Double suggestedAmountOfLoan) {
		this.suggestedAmountOfLoan = suggestedAmountOfLoan;
	}
	
	
}
