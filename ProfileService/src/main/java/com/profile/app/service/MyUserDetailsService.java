package com.profile.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.profile.app.model.UserProfile;
import com.profile.app.repository.ProfileRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.profile.app.model.UserProfile userDb=profileRepository.findByUserName(username);
		if(userDb==null) {
			throw new UsernameNotFoundException("Invalid credentials");
		}
		List<GrantedAuthority> list=new ArrayList<>();
		String role=userDb.getRole();
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role);
		list.add(authority);
		User springUser=new User(userDb.getUserName(),userDb.getPassword(),list);
		return springUser;
		
	}
	
	

}
