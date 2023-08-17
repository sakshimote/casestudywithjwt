package com.profile.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.profile.app.exception.AddUserException;
import com.profile.app.model.UserProfile;


public interface ProfileService {

	public UserProfile addNewCustomerProfile(UserProfile userProfile) throws AddUserException;
	
	public List<UserProfile> getAllProfiles();
	
	public UserProfile getByProfileId(String Id);
	
	public UserProfile getByUsername(String username);
	
	public UserProfile updateProfile(UserProfile userProfile,String userId);
	
	public String deleteProfile(String userId);
	
	public List<UserProfile> findByMobileNo(Long mobileNo);
	
	public List<UserProfile> findByEmail(String email);
}

