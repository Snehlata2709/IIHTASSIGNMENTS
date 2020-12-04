package com.iiht.training.eloan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.iiht.training.eloan.Util.ParserModelEntity;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.ProcessingDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.exception.AlreadyProcessedException;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allAppliedLoans() {
		// TODO Auto-generated method stub
		//return null;
		List<LoanOutputDto> listAllAppliedLoans= loanRepository.findAllByStatus(0).stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());		
		if(listAllAppliedLoans.isEmpty())
		{		
			
			throw new LoanNotFoundException(" Applied loans- Found None");
			
		}
		return listAllAppliedLoans;
		//return loanRepository.findAllByStatus(0).stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());
	}

	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto) {
		// TODO Auto-generated method stub		
			if(! (usersRepository.existsById(clerkId)) || !(usersRepository.findAllById(clerkId).getRole().equals("Clerk") ))
			{
				throw new ClerkNotFoundException("May be this ClerkId does not exists or selected id is not ClerkId.Please verify.");				
			}
			
			 if (loanRepository.findAllByloanApplicationNumber(loanAppId)==null)
			{
				throw new LoanNotFoundException("LoanApplicationNumber seems to be invalid, Please enter correct one");
			}
			 else {					 
					 if(loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(1) || 
							loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(2) ||
							loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(-1))
					{
						throw new AlreadyProcessedException("LoanApplicationNumber is already processed/Sanctioned/Rejected");
					}
			 }	 
				ProcessingDto processingDto1 = ParserModelEntity.parse(clerkId,loanAppId,pProcessingInfoRepository.
						save(ParserModelEntity.parse(clerkId,loanAppId,processingDto)));				

				Loan loanU = loanRepository.findAllByloanApplicationNumber(loanAppId);
				loanU.setStatus(1);				
				loanRepository.save(loanU);
				
				//LoanOutputDto loanOutputDtoPro = new LoanOutputDto();
				//loanOutputDtoPro.setProcessingDto(ParserModelEntity.parse(pProcessingInfoRepository.findAllByloanAppId(loanAppId)));
				
				return processingDto1;
			
			
		
		
		
	}

}
