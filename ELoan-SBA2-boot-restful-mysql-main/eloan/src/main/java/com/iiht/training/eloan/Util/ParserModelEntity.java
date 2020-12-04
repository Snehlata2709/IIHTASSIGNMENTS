/**
 * 
 */
package com.iiht.training.eloan.Util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.dto.RejectDto;
import com.iiht.training.eloan.dto.SanctionDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.SanctionInfo;
import com.iiht.training.eloan.entity.Users;



/**
 * @author wave2wf62
 *
 */
public class ParserModelEntity {
	
	public static Users parse(UserDto source) {
		Users target = new Users();
		//LoanOutputDto loanODobj = new LoanOutputDto();
		target.setId(source.getId());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setMobile(source.getMobile());
		target.setRole(source.getRole());
		//loanODobj.setUserDto(source);
		return target;
	}
	
	public static UserDto parse(Users source) {
		UserDto target = new UserDto();
		target.setId(source.getId());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setMobile(source.getMobile());
		target.setRole(source.getRole());
		return target;
	}

	
	public static Loan parse(LoanOutputDto source) {
		
		String sysdate= DateTimeFormatter.ofPattern("uuuuMMddHHmmss").format(LocalDateTime.now());			
		String sysdate1= DateTimeFormatter.ofPattern("dd-MM-uuuu").format(LocalDateTime.now());	
		
		Loan target = new Loan();		
		target.setCustomerId(source.getCustomerId());
		target.setBillingIndicator(source.getLoanDto().getBillingIndicator());	
		target.setBusinessStructure(source.getLoanDto().getBusinessStructure());
		target.setLoanAmount(source.getLoanDto().getLoanAmount());
		target.setLoanApplicationDate(sysdate1); // Default set to SYsdate
		//target.setLoanApplicationDate(source.getLoanDto().getLoanApplicationDate()); //provides flexibility to take from customer
		target.setTaxIndicator(source.getLoanDto().getTaxIndicator());
		target.setLoanName(source.getLoanDto().getLoanName());
		target.setRemark(source.getRemark());
		target.setStatus(source.getStatus());
			
		String loanApplicationNumber1 = target.getCustomerId() + sysdate;
		Long loanApplicationNumber =Long.parseLong(loanApplicationNumber1);
		target.setLoanApplicationNumber(loanApplicationNumber);			
		target.setAddress(source.getLoanDto().getAddress());
		target.setEmail(source.getLoanDto().getEmail());
		target.setMobile(source.getLoanDto().getMobile());
		
		return target;
	}
	

		public static LoanOutputDto parse(Loan source) {
		//LoanOutputDto target = new LoanOutputDto();		
		LoanDto target1 = new LoanDto();
		
		LoanOutputDto target = new LoanOutputDto();
		target1.setBillingIndicator(source.getBillingIndicator());	
		target1.setBusinessStructure(source.getBusinessStructure());
		target1.setLoanAmount(source.getLoanAmount());
		target1.setLoanApplicationDate(source.getLoanApplicationDate());
		target1.setTaxIndicator(source.getTaxIndicator());
		target1.setLoanName(source.getLoanName());
		target1.setAddress(source.getAddress());
		target1.setEmail(source.getEmail());
		target1.setMobile(source.getMobile());		
		target.setLoanDto(target1);		
		target.setCustomerId(source.getCustomerId());
		target.setLoanAppId(source.getLoanApplicationNumber());
		target.setStatus(source.getStatus());
		target.setRemark(source.getRemark());
		
//		UserDto userDto= new UserDto();
//		ProcessingDto processingDto = new ProcessingDto();
//		SanctionOutputDto sanctionOutputDto= new SanctionOutputDto();
//		target.setUserDto(userDto);
//		target.setProcessingDto(processingDto);
//		target.setSanctionOutputDto(sanctionOutputDto);
		
		return target;
	}
	
		
		public static ProcessingDto parse(Long clerkId, Long loanAppId, ProcessingInfo source) {
		ProcessingDto target = new ProcessingDto();
		target.setAcresOfLand(source.getAcresOfLand());	
		target.setAddressOfProperty(source.getAddressOfProperty());
		target.setAppraisedBy(source.getAppraisedBy());
		target.setLandValue(source.getLandValue());
		target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
		target.setValuationDate(source.getValuationDate());		
		return target;
		}
		
		public static ProcessingInfo parse(Long clerkId, Long loanAppId, ProcessingDto source) {
			String sysdate2= DateTimeFormatter.ofPattern("dd-MM-uuuu").format(LocalDateTime.now());	
			ProcessingInfo target = new ProcessingInfo();
			target.setAcresOfLand(source.getAcresOfLand());	
			target.setAddressOfProperty(source.getAddressOfProperty());
			target.setAppraisedBy(source.getAppraisedBy());
			target.setLandValue(source.getLandValue());
			target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
			//target.setValuationDate(source.getValuationDate());	
			target.setValuationDate(sysdate2);
			target.setLoanAppId(loanAppId);
			target.setLoanClerkId(clerkId);
			return target;
		}
		
		public static ProcessingDto parse(ProcessingInfo source) {
			//String sysdate2= DateTimeFormatter.ofPattern("dd-MM-uuuu").format(LocalDateTime.now());	
			ProcessingDto target = new ProcessingDto();
			target.setAcresOfLand(source.getAcresOfLand());	
			target.setAddressOfProperty(source.getAddressOfProperty());
			target.setAppraisedBy(source.getAppraisedBy());
			target.setLandValue(source.getLandValue());
			target.setSuggestedAmountOfLoan(source.getSuggestedAmountOfLoan());
			//target.setValuationDate(source.getValuationDate());	
			target.setValuationDate(source.getValuationDate());			
			return target;
		}
		
	
		public static SanctionInfo parse(Long managerId, Long loanAppId, SanctionOutputDto source) {
			SanctionInfo target = new SanctionInfo();
			target.setLoanAmountSanctioned(source.getLoanAmountSanctioned());
			target.setLoanAppId(loanAppId);			
			target.setManagerId(managerId);			
			target.setPaymentStartDate(source.getPaymentStartDate());
			target.setTermOfLoan(source.getTermOfLoan());
			//To calculate LoanClosureDate
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.ENGLISH);
			LocalDate date=LocalDate.parse(source.getPaymentStartDate(),dtf);		
			LocalDate loanclosedate= date.plusMonths(new Double(source.getTermOfLoan()).longValue());
			String loanClosureDate=dtf.format(loanclosedate);		
			target.setLoanClosureDate(loanClosureDate);
			//To Calculate MonthlyPayment
			Double termPaymentAmount= source.getLoanAmountSanctioned()*Math.pow(  (1+(11.5)/100),(source.getTermOfLoan())   );
			Double monthlyPayment = termPaymentAmount/(source.getTermOfLoan());
			DecimalFormat df= new DecimalFormat("#");
			df.setMaximumFractionDigits(5);		
			target.setMonthlyPayment(Double.parseDouble(df.format(monthlyPayment)));
			return target;
			
		}
		public static SanctionOutputDto parse(Long managerId, Long loanAppId, SanctionInfo source) {
			SanctionOutputDto target = new SanctionOutputDto();
			target.setLoanAmountSanctioned(source.getLoanAmountSanctioned());					
			target.setMonthlyPayment(source.getMonthlyPayment());
			target.setLoanClosureDate(source.getLoanClosureDate());			
			target.setPaymentStartDate(source.getPaymentStartDate());
			target.setTermOfLoan(source.getTermOfLoan());			
			return target;
			
		}
		public static SanctionOutputDto parse(SanctionInfo source) {
			SanctionOutputDto target = new SanctionOutputDto();
			target.setLoanAmountSanctioned(source.getLoanAmountSanctioned());					
			target.setMonthlyPayment(source.getMonthlyPayment());
			target.setLoanClosureDate(source.getLoanClosureDate());			
			target.setPaymentStartDate(source.getPaymentStartDate());
			target.setTermOfLoan(source.getTermOfLoan());			
			return target;
			
		}

}
