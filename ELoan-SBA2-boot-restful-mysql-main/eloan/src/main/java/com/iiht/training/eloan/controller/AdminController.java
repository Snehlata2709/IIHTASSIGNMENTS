package com.iiht.training.eloan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.eloan.dto.UserDto;
import com.iiht.training.eloan.dto.exception.ExceptionResponse;
import com.iiht.training.eloan.exception.GlobalHandler;
import com.iiht.training.eloan.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register-clerk")
	public ResponseEntity<UserDto> registerClerk(@Valid @RequestBody UserDto userDto) throws ExceptionResponse {
		return new ResponseEntity<>(adminService.registerClerk(userDto),HttpStatus.OK);
	}
	
	@PostMapping("/register-manager")
	public ResponseEntity<UserDto> registerManager(@Valid @RequestBody UserDto userDto) throws ExceptionResponse{
		return new ResponseEntity<>(adminService.registerManager(userDto),HttpStatus.OK);
	}
	
	@GetMapping("/all-clerks")
	public ResponseEntity<List<UserDto>> getAllClerks() throws ExceptionResponse{
		return new ResponseEntity<>(adminService.getAllClerks(),HttpStatus.OK);
	}
	
	@GetMapping("/all-managers")
	public ResponseEntity<List<UserDto>> getAllManagers() throws ExceptionResponse{
		return new ResponseEntity<>(adminService.getAllManagers(),HttpStatus.OK);
	}
	
	
}
