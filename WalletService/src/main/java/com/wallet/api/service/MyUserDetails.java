package com.wallet.api.service;

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
import org.springframework.web.client.RestTemplate;

import com.wallet.api.model.UserProfile;


@Service
public class MyUserDetails implements UserDetailsService {
	@Autowired
	private RestTemplate restTemplate;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserProfile profile=restTemplate.getForObject("http://localhost:8095/api/user/username/"+username, UserProfile.class);
		if(profile==null) {
			throw new UsernameNotFoundException("user with username "+username+" not found");
		}
		List<GrantedAuthority> list=new ArrayList<>();
		String role=profile.getRole();
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role);
		list.add(authority);
		
		User springUser=new User(username,profile.getPassword(),list);
		return springUser;
		
	}
}
