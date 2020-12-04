package com.iiht.training.eloan.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
	private Long id;
	
	@NotNull(message="FirstName is mandatory")
	@Size(min=3,max=100,message="FirstName must be 3 to 100 chars in length")
	private String firstName;
	
	@NotNull(message="LastName is mandatory")
	@Size(min=3,max=100,message="LastName must be 3 to 100 chars in length")
	private String lastName;
	
	@NotNull(message="Email is mandatory")
	@Size(min=3,max=100,message="Email must be 3 to 100 chars in length")
	@Email(message = "Email Id is not Valid")
	private String email;
	
	@NotNull(message="Mobile is mandatory")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile number must be exactly ten digits")	
	private String mobile;
	
	private String role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
