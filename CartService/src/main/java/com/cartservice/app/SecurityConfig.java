//package com.cartservice.app;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.cartservice.app.service.MyUserDetails;
//
//
//@Configuration
//public class SecurityConfig {
//
//	@Autowired
//	private MyUserDetails myUserDetails;
// 
//	 @Bean
//	    public UserDetailsService userDetailsService() {
//	        return new MyUserDetails();
//	    }
//	 
//	 @Bean
//	    public BCryptPasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	 
//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//			http.authorizeRequests()
//		 
//			.antMatchers(HttpMethod.GET,"/cart/getCart/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/cart/addcart/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/cart/add/quantity/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/cart/remove/quantity/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/cart/add/items/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/cart/carts").authenticated()
//			.antMatchers(HttpMethod.GET,"/cart/byUser/**").permitAll()
//			.antMatchers(HttpMethod.PUT,"/cart/removeAll/items/**").authenticated()
//			.antMatchers(HttpMethod.PUT,"/cart/remove/item/**").authenticated()
//		
//
//			.and()
//			.httpBasic()
//			.and().csrf().disable()
//			.cors().disable();
//		
//			http.authenticationProvider(authenticationProvider());
//		 	return http.build();
//	    }
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
//
//}
