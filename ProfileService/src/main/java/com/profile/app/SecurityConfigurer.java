package com.profile.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.profile.app.filter.JwtRequestFilter;
import com.profile.app.service.MyUserDetailsService;


@Configuration
public class SecurityConfigurer {
 
	@Autowired
	private MyUserDetailsService springUserService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	protected AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();		

	}
	
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(springUserService);
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(getAuthProvider());
		http.authorizeRequests()
		 	.antMatchers(HttpMethod.POST,"/api/user").permitAll()
			.antMatchers(HttpMethod.POST,"/api/authenticate").permitAll()
			.antMatchers(HttpMethod.GET,"/api/user").permitAll()
			.antMatchers(HttpMethod.GET,"/api/user/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/api/user/*").authenticated()
			.antMatchers(HttpMethod.DELETE,"/api/user/*").authenticated()
			.antMatchers(HttpMethod.GET,"/api/user/mobileno/*").authenticated()
			.antMatchers(HttpMethod.GET,"/api/user/email/*").authenticated()
			.antMatchers(HttpMethod.GET,"/api/user/username/*").permitAll()
			.anyRequest().authenticated().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		http.cors().and().csrf().disable();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}
	
	 @Bean
	   public WebSecurityCustomizer webSecurityCustomizer() {
	       return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	   }
	 @Bean
	    public PasswordEncoder passwordEncoder() {
		 PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
	    }
	
}
