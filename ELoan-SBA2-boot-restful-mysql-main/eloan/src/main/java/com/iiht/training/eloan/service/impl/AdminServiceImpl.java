package com.iiht.training.eloan.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.Util.ParserModelEntity;
import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.GlobalHandler;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.ManagerNotFoundException;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.AdminService;




@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDto registerClerk(UserDto userDto)  {
		// TODO Auto-generated method stub
		if(userDto!=null)
		{
			
			userDto.setRole("Clerk");
			userDto = ParserModelEntity.parse(usersRepository.save(ParserModelEntity.parse(userDto)));
			
		}
		return userDto;
	}

	@Override
	public UserDto registerManager(UserDto userDto) {
		// TODO Auto-generated method stub
		if(userDto!=null)
		{
			
			userDto.setRole("Manager");
			userDto = ParserModelEntity.parse(usersRepository.save(ParserModelEntity.parse(userDto)));	
			
		}
		return userDto;
	}

	@Override
	public List<UserDto> getAllClerks() {
		// TODO Auto-generated method stub	
		
		return usersRepository.findAllByRole("Clerk").stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getAllManagers() {
		// TODO Auto-generated method stub
		return usersRepository.findAllByRole("Manager").stream().map(e -> ParserModelEntity.parse(e)).collect(Collectors.toList());
	}

}
