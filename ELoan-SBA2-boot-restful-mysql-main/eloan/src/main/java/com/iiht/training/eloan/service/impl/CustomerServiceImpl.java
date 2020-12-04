package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.Util.ParserModelEntity;
import com.iiht.training.eloan.dto.LoanDto;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	
	
	@Override
	public UserDto register(UserDto userDto) {
		// TODO Auto-generated method stub
		if(userDto!=null)
		{			
			userDto.setRole("Customer");
			
			userDto = ParserModelEntity.parse(usersRepository.save(ParserModelEntity.parse(userDto)));	
			
		}		
		return userDto;
	}

	@Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto) {
		// TODO Auto-generated method stub
		LoanOutputDto loanOutputDto = new LoanOutputDto();// original
		loanOutputDto.setCustomerId(customerId);
		loanOutputDto.setLoanDto(loanDto);		
		loanOutputDto.setStatus(0);		
		
		if(usersRepository.existsById(customerId))
		{	
			if(usersRepository.findAllById(customerId).getRole().equals("Customer")) //check only customer can apply for loan 
			{
			loanOutputDto =(ParserModelEntity.parse(loanRepository.save(ParserModelEntity.parse(loanOutputDto))));
			
			loanOutputDto.setUserDto(ParserModelEntity.parse(usersRepository.findAllById(loanRepository.findAllBycustomerId(customerId).get(0)
					.getCustomerId())));
			}
			else
			{
				throw new CustomerNotFoundException("Selected id is not CustomerId's, Please verify and re-enter");	
			}
		}
		else
		{
			 throw new CustomerNotFoundException("This CustomerID does not  exists");		
		}
						
		return loanOutputDto;
		
	}

	@Override
	public LoanOutputDto getStatus(Long loanAppId)  {
		// TODO Auto-generated method stub
//		 
	if (loanRepository.findAllByloanApplicationNumber(loanAppId) == null)
	{
					throw new LoanNotFoundException("LoanApplicationNumber seems to be invalid, Please enter corect one");
	}
	else
	{   
		loanOutputDtoUpdate loanOutputDtoUpdateobj = new loanOutputDtoUpdate();
		LoanOutputDto loanOutputDto = loanOutputDtoUpdateobj.loanOutputDtoUpdateParse(loanAppId,usersRepository,loanRepository,pProcessingInfoRepository,sanctionInfoRepository);
		return loanOutputDto;				
				
	}
		
	}
	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) {
		// TODO Auto-generated method stub	
		if(! (usersRepository.existsById(customerId)  ) || !(usersRepository.findAllById(customerId).getRole().equals("Customer")))
		{
			throw new CustomerNotFoundException("May be this CustomerId does not exists or selected id is not Customer.Please verify.");
		}
		loanOutputDtoUpdate loanOutputDtoUpdateobj = new loanOutputDtoUpdate();
		List<LoanOutputDto> loanOutputDtoList = loanOutputDtoUpdateobj.loanOutputDtoUpdateParseCid(customerId,usersRepository,loanRepository,pProcessingInfoRepository,sanctionInfoRepository);		
		return loanOutputDtoList;
		

	}

}
