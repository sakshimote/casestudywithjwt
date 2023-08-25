//package com.profile.app;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.profile.app.service.MyUserDetailsService;
//
//@Configuration
//public class SecurityConfig  {
//	
//	 @Bean
//	    public UserDetailsService userDetailsService() {
//	        return new MyUserDetailsService();
//	    }
//	 
//	 @Bean
//	    public BCryptPasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	 
//	 @Bean
//	
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//			http.authorizeRequests()
//		 .antMatchers(HttpMethod.POST,"/api/user").permitAll()
//			.antMatchers(HttpMethod.GET,"/api/user").permitAll()
//			.antMatchers(HttpMethod.GET,"/api/user/**").permitAll()
//			.antMatchers(HttpMethod.PUT,"/api/user/**").authenticated()
//			.antMatchers(HttpMethod.DELETE,"/api/user/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/user/mobileno/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/user/email/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/user/username/**").permitAll()
//
//		
//			.and()
//			.httpBasic()
//			.and().csrf().disable()
//			.cors().disable();
//
//			http.authenticationProvider(authenticationProvider());
//		 	return http.build();
//	 }
//	     
//	    @Bean
//	    public WebSecurityCustomizer webSecurityCustomizer() {
//	    	return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
//	         
//	    }
//	    @Bean
//	    public AuthenticationManager authenticationManager(
//	            AuthenticationConfiguration authConfig) throws Exception {
//	        return authConfig.getAuthenticationManager();
//	    }
//	    @Bean
//	    public DaoAuthenticationProvider authenticationProvider() {
//	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	         
//	        authProvider.setUserDetailsService(userDetailsService());
//	        authProvider.setPasswordEncoder(passwordEncoder());
//	     
//	        return authProvider;
//	    }
//   
//
//}
