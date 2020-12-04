package com.iiht.training.eloan.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.Util.ParserModelEntity;
import com.iiht.training.eloan.dto.LoanOutputDto;
import com.iiht.training.eloan.dto.RejectDto;
import com.iiht.training.eloan.dto.SanctionDto;
import com.iiht.training.eloan.dto.SanctionOutputDto;
import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.exception.AlreadyFinalizedException;
import com.iiht.training.eloan.exception.AlreadyProcessedException;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.exception.ManagerNotFoundException;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository pProcessingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allProcessedLoans() {
		// TODO Auto-generated method stub
		//return loanRepository.findAllByStatus(1).stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());
		List<LoanOutputDto> listAllProcessedLoans= loanRepository.findAllByStatus(1).stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());		
		if(listAllProcessedLoans.isEmpty())
		{		
			throw new LoanNotFoundException("Proccessed loans: Found None");
		}
		return listAllProcessedLoans;
	}

	@Override
	public RejectDto rejectLoan(Long managerId, Long loanAppId, RejectDto rejectDto) {
		
		// TODO Auto-generated method stub
		
		if( !(usersRepository.existsById(managerId)) || !(usersRepository.findAllById(managerId).getRole().equals("Manager")) )
		{
			
			throw new ManagerNotFoundException("May be this ManagerId does not exists or selected id is not ManagerId.Please verify.");				
		}
		
		 if (loanRepository.findAllByloanApplicationNumber(loanAppId)==null)
		{
			throw new LoanNotFoundException("LoanApplicationNumber seems to be invalid, Please enter correct one");
		}
		 else {
			 	if(loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(0) || 
			 			loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(-1))					
				{
					throw new AlreadyProcessedException("LoanApplicationNumber is either not processed or already rejected.Hence, it cannot be rejected");
				}
				if( loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(2))
				{
					throw new AlreadyFinalizedException("LoanApplicationNumber is already Sanctioned.Hence, it cannot be rejected");
				}
		 }
		

		 	Loan loanU = loanRepository.findAllByloanApplicationNumber(loanAppId);
			loanU.setStatus(-1);			
			loanU.setRemark(rejectDto.getRemark());
			loanRepository.save(loanU);
			rejectDto.setRemark(loanU.getRemark());

			return rejectDto;			

	}

	@Override
	public SanctionOutputDto sanctionLoan(Long managerId, Long loanAppId, SanctionDto sanctionDto) {
		// TODO Auto-generated method stub
		if( !(usersRepository.existsById(managerId)) || !(usersRepository.findAllById(managerId).getRole().equals("Manager")) )
		{
			
			throw new ManagerNotFoundException("May be this ManagerId does not exists or selected id is not ManagerId.Please verify.");				
		}
		 if (loanRepository.findAllByloanApplicationNumber(loanAppId)==null)
		{
			throw new LoanNotFoundException("LoanApplicationNumber seems to be invalid, Please enter correct one");
		}
		 else {
			 	if(loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(0) || 
			 			loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(-1))					
				{
					throw new AlreadyProcessedException("LoanApplicationNumber is either not processed or already rejected.Hence, it cannot be Sanctioned");
				}
				if( loanRepository.findAllByloanApplicationNumber(loanAppId).getStatus().equals(2))
				{
					throw new AlreadyFinalizedException("LoanApplicationNumber is already Sanctioned.Hence, it cannot be rejected");
				}
		 }
		
		Loan loanU = loanRepository.findAllByloanApplicationNumber(loanAppId);
		loanU.setStatus(2);
		loanRepository.save(loanU);
		
		SanctionOutputDto sanctionOutputDto = new SanctionOutputDto();
		sanctionOutputDto.setLoanAmountSanctioned(sanctionDto.getLoanAmountSanctioned());
		sanctionOutputDto.setPaymentStartDate(sanctionDto.getPaymentStartDate());
		sanctionOutputDto.setTermOfLoan(sanctionDto.getTermOfLoan());		
				
		return ParserModelEntity.parse(managerId,loanAppId,sanctionInfoRepository.
				save(ParserModelEntity.parse(managerId,loanAppId,sanctionOutputDto)));

}
}
