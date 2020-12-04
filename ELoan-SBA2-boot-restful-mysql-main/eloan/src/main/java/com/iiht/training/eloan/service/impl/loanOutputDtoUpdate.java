package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.Util.ParserModelEntity;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;


public class loanOutputDtoUpdate {

	
	public  LoanOutputDto loanOutputDtoUpdateParse(Long loanAppId,UsersRepository usersRepository1,LoanRepository
			loanRepository1,ProcessingInfoRepository pProcessingInfoRepository1,SanctionInfoRepository sanctionInfoRepository1)
	{
	
	LoanOutputDto loanOutputDto= new LoanOutputDto();	
//	if((loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus().equals(0))
//	{
//	loanOutputDto.setUserDto(ParserModelEntity.parse(usersRepository1.findAllById(loanRepository1.findAllByloanApplicationNumber(loanAppId)
//		.getCustomerId())));
//	loanOutputDto.setLoanDto(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getLoanDto());
//	}
//	else if((loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus().equals(1) || (loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus().equals(-1))
//	{
//		loanOutputDto.setUserDto(ParserModelEntity.parse(usersRepository1.findAllById(loanRepository1.findAllByloanApplicationNumber(loanAppId)
//				.getCustomerId())));
//		loanOutputDto.setLoanDto(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getLoanDto());
//		loanOutputDto.setProcessingDto(ParserModelEntity.parse(pProcessingInfoRepository1.findAllByloanAppId(loanAppId)));
//	}
//	else if((loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus().equals(2))
//	{
//		loanOutputDto.setUserDto(ParserModelEntity.parse(usersRepository1.findAllById(loanRepository1.findAllByloanApplicationNumber(loanAppId)
//				.getCustomerId())));
//		loanOutputDto.setLoanDto(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getLoanDto());
//		loanOutputDto.setProcessingDto(ParserModelEntity.parse(pProcessingInfoRepository1.findAllByloanAppId(loanAppId)));
//		loanOutputDto.setSanctionOutputDto(ParserModelEntity.parse(sanctionInfoRepository1.findAllByloanAppId(loanAppId)));
//	}
//	else
//	{
//		throw new LoanNotFoundException("Loan not found");
//	}
	
	loanOutputDto.setCustomerId(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getCustomerId());
	loanOutputDto.setLoanAppId(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getLoanAppId());	
	loanOutputDto.setStatus(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus());	
	if((loanRepository1.findAllByloanApplicationNumber(loanAppId)).getStatus().equals(-1))
		{
			loanOutputDto.setRemark(ParserModelEntity.parse(loanRepository1.findAllByloanApplicationNumber(loanAppId)).getRemark());
		}	
	
	return loanOutputDto;
	}
	
	public  List<LoanOutputDto> loanOutputDtoUpdateParseCid(Long customerId,UsersRepository usersRepository1,LoanRepository
			loanRepository1,ProcessingInfoRepository pProcessingInfoRepository1,SanctionInfoRepository sanctionInfoRepository1)
	{
	
	List<Loan> customerList=loanRepository1.findAllBycustomerId(customerId);
	List<LoanOutputDto> loanOutputDtoList=new ArrayList<LoanOutputDto>();
	for(int i=0;i<customerList.size();i++)
	{
		Long loanAppId=loanRepository1.findAllBycustomerId(customerId).get(i).getLoanApplicationNumber();
		loanOutputDtoList.add(i, loanOutputDtoUpdateParse( loanAppId, usersRepository1,loanRepository1, pProcessingInfoRepository1, sanctionInfoRepository1));
	}
	return loanOutputDtoList;
	}
	

}
