package com.profile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctc.wstx.shaded.msv_core.grammar.AnyNameClass;
import com.profile.app.controller.ProfileController;
import com.profile.app.exception.AddUserException;
import com.profile.app.model.Address;
import com.profile.app.model.UserProfile;
import com.profile.app.service.ProfileServiceImpl;


@SpringBootTest
class ProfileServiceApplicationTests {

	@InjectMocks
	ProfileController profileController;
	
	@Mock
	ProfileServiceImpl profileServiceImpl;
	
	UserProfile userProfile;
	
	Address address;
	
	List<UserProfile> listUser;
	
	private final String userId = "1234";
	
	private final String userName="ross";
	
	private final Long MobileNo = 1231231235L;
	
	private final String emailId = "ross@gamil.com";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		address = new Address();
		address.setHouseNumber(12);
		address.setColonyName("ABC colony");
		address.setCity("Pune");
		address.setPinCode(413133L);
		address.setState("Maharashtra");
		address.setStreetName("xyz street");
		
		
		userProfile = new UserProfile();
		userProfile.setId(userId);
		userProfile.setFullName("ross geller");
		userProfile.setEmail("ross@gamil.com");
		userProfile.setDateOfBirth(LocalDate.of(2011, 11, 21));
		userProfile.setGender("Male");
		userProfile.setMobileNo(1231231235L);
		userProfile.setRole("Customer");
		userProfile.setAddress(address);
		userProfile.setPassword("acs47%ksd09&@ksd");
		userProfile.setUserName("ross");
		
		
		listUser = new ArrayList<UserProfile>();
		
		listUser.add(userProfile);
		
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void addUser() throws AddUserException {
		// first check that all the required parameters are in the profile class
		assertNotNull(userProfile,"user is null");
		assertNotNull(userProfile.getFullName(),"Full name required");
		assertNotNull(userProfile.getEmail(),"Email is required");
		assertNotNull(userProfile.getMobileNo(),"Mobile Number is required");
		assertNotNull(userProfile.getAddress(),"Address is required");
		assertNotNull(userProfile.getPassword(),"Password is required");
		assertNotNull(userProfile.getRole(),"Role is required");
		assertNotNull(userProfile.getUserName(),"Username is required");

		assertEquals(10, userProfile.getMobileNo().toString().length());
		
		// get the userProfile by when then method
		when(profileServiceImpl.addNewCustomerProfile(userProfile)).thenReturn(userProfile);
		
		UserProfile userRest = profileController.addNewCustomerProfile(userProfile);
		
		assertNotNull(userRest);
		
		assertEquals(userProfile.getFullName(), userRest.getFullName());
		assertEquals(userProfile.getEmail(), userRest.getEmail());
		assertEquals(userProfile.getMobileNo(), userRest.getMobileNo());
		assertEquals(userProfile.getAddress(), userRest.getAddress());
		assertEquals(userProfile.getPassword(), userRest.getPassword());
		assertEquals(userProfile.getRole(), userRest.getRole());
		assertEquals(userProfile.getUserName(), userRest.getUserName());
	}

	
	@Test
	void getUserById() {
		when(profileServiceImpl.getByProfileId(userId)).thenReturn(userProfile);
		
		UserProfile userRest = profileController.getByProfileId(userId);
		
		assertNotNull(userRest,"User not available in DB");
		
		assertEquals(userProfile.getId(), userRest.getId());
		
	}
	
	@Test
	 void getAllProfiles() {
		
		
		when(profileServiceImpl.getAllProfiles()).thenReturn(listUser);//mocking
		assertEquals(1, profileServiceImpl.getAllProfiles().size()); 
	
	}
	
	
	@Test
	void updateUser() {
		when(profileServiceImpl.getByProfileId(userId)).thenReturn(userProfile);
		when(profileServiceImpl.updateProfile(userProfile, userId)).thenReturn(userProfile);
		
		UserProfile userRest = profileController.updateProfile(userProfile, userId);
		
		assertNotNull(userRest,"User not available in DB/ id incorrect");
		
		assertEquals(userProfile.getId(), userRest.getId());
		
	}
	
// need to validate in before deleting that user is present or not in db
	@Test
	void deleteUser() throws Exception{
		
		when(profileServiceImpl.deleteProfile(userId)).thenReturn("Deleted Succesfully");
		
		when(profileServiceImpl.getByProfileId(userId)).thenReturn(userProfile);

		String delete = profileController.deleteProfile(userId);
		
		assertEquals("Deleted Succesfully", delete,"user not available in db");
		
		
		
	}
	
	@Test
	void findByMobileNo() {
		
		when(profileServiceImpl.findByMobileNo(MobileNo)).thenReturn(listUser);
		
		userProfile = profileController.findByMobileNo(MobileNo);
	
		assertNotNull(userProfile);
		
		assertEquals(userProfile.getMobileNo(), 1231231235L);
		
	}
	
	@Test
	void findByEmailId() {
		
		when(profileServiceImpl.findByEmail(emailId)).thenReturn(listUser);
		
		userProfile= profileController.findByEmail(emailId);

		assertNotNull(userProfile);
		
		assertEquals(userProfile.getEmail(), "ross@gamil.com");
		
	}
	
	@Test
	void getByUsername() {
		when(profileServiceImpl.getByUsername(userName)).thenReturn(userProfile);
		userProfile=profileController.findByUsername(userName);
		assertNotNull(userProfile);
		assertEquals(userProfile.getUserName(), "ross");
		
	}
	
	
	
	
	
	
}
