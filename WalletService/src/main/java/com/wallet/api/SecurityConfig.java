//package com.wallet.api;
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
//import com.wallet.api.service.MyUserDetails;
//
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
//			.antMatchers(HttpMethod.GET,"/api/wallets").authenticated()
//			.antMatchers(HttpMethod.POST,"/api/wallet/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/api/add/wallet/**/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/wallets/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/pay/byWallet/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/api/payment/history/").authenticated()
//			.antMatchers(HttpMethod.POST,"/pg/createOrder").permitAll()
//			
//			.and()
//			.httpBasic()
//			.and().csrf().disable()
//			.cors().disable();
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
