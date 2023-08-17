package com.profile.app.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document
@ApiModel(description = "Details about the contact")
public class UserProfile {

	@Id 
	@ApiModelProperty(notes = "unique id of user profile")
	private String id;
	
	@Field
	
	private String fullName;
	
	@Field
	@Indexed(unique = true)
	private String userName;
	
	private String plainTextPassword;

	@Field
	private String email;
	
	@Field
	private Long mobileNo;
	
	@Field
	private String role;
	
	@Field
	private LocalDate dateOfBirth;
	
	@Field
	private String gender;
	
	@Field
	private String password;
	
	@Field
	private Address address;
	

	
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



	public UserProfile(String id, String fullName, String userName, String plainTextPassword, String email,
			Long mobileNo, String role, LocalDate dateOfBirth, String gender, String password, Address address) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.plainTextPassword = plainTextPassword;
		this.email = email;
		this.mobileNo = mobileNo;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
		this.address = address;
	}





	public String getPlainTextPassword() {
		return plainTextPassword;
	}





	public void setPlainTextPassword(String plainTextPassword) {
		this.plainTextPassword = plainTextPassword;
	}





	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "UserProfile [Id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", role=" + role + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", password=" + password
				+ "]";
	}



	
	
}
