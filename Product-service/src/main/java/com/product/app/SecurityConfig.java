//package com.product.app;
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
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.product.app.service.MyUserDetails;
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
//			.antMatchers(HttpMethod.GET,"/product/allproduct").permitAll()
//		    .antMatchers(HttpMethod.POST,"/review/addReview/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/product/allproduct/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/product/allproduct/**").permitAll()
//			.antMatchers(HttpMethod.GET,"/product/allproduct/name/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/product/allproduct/type/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/product/allproduct/category/**").authenticated()
//			.antMatchers(HttpMethod.PUT,"/product/allproduct/**").authenticated()
//			.antMatchers(HttpMethod.DELETE,"/product/allproduct/**").permitAll()
//			
//			.antMatchers(HttpMethod.GET,"/category/categories").permitAll()
//			.antMatchers(HttpMethod.GET,"/category/category/**").authenticated()
//			.antMatchers(HttpMethod.POST,"/category/addCategory").authenticated()
//			.antMatchers(HttpMethod.GET,"/category/category/byId/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/category/delete/**").permitAll()
//			
//			.antMatchers(HttpMethod.POST,"/review/addReview/**").authenticated()
//			.antMatchers(HttpMethod.GET,"/review/reviews/**").authenticated()
//			.antMatchers(HttpMethod.DELETE,"/review/delete/**").permitAll()
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
