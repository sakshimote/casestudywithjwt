package com.order.app;

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

import com.order.app.filter.JwtRequestFilter;
import com.order.app.service.MyUserDetails;


@Configuration
public class SecurityConfigurer {
 


	@Autowired
	private MyUserDetails springUserService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	protected AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
		
		return authConfig.getAuthenticationManager();		
//		auth.inMemoryAuthentication()
//		.withUser("harry").password(getEncryptPassword().encode("potter"))
//		.authorities("Customer")
//		.and()
//		.withUser("ronald").password(getEncryptPassword().encode("weasely"))
//		.authorities("Merchant");	
	}
	
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(springUserService);
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}
//	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		
//		return new WebMvcConfigurer() {
//			public void addCorsMappings(CorsRegistry corsRegistry) {
//				corsRegistry.addMapping("*").allowedHeaders("*"
//						).allowedOrigins("*").allowCredentials(true).exposedHeaders("Authorization");
//				
//			}
//		};
//		
//	}
//	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(getAuthProvider());
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/authenticate").permitAll()
			.antMatchers(HttpMethod.GET,"/api/allorder").authenticated()
		.antMatchers(HttpMethod.POST,"/api/order/*").authenticated()
			.antMatchers(HttpMethod.GET,"/api/allorder/*").authenticated()
		.antMatchers(HttpMethod.GET,"/api/allorder/byuser/*").authenticated()
			
		   
				.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				//.and()
				//.httpBasic()
//			.and()
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