package com.profile.app.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.profile.app.model.UserProfile;


@Document
public interface ProfileRepository extends MongoRepository<UserProfile, String>{

	List<UserProfile> findByMobileNo(Long mobileNo);
	
	List<UserProfile> findByEmail(String email);



	UserProfile findByUserName(String userName); 

	

}
