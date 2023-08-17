package com.product.app;

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

import com.product.app.filter.JwtRequestFilter;
import com.product.app.service.MyUserDetails;

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
			.antMatchers(HttpMethod.POST,"/product/authenticate").permitAll()
			.antMatchers(HttpMethod.GET,"/product/allproduct").permitAll()
		    .antMatchers(HttpMethod.POST,"/review/addReview/*").authenticated()
			.antMatchers(HttpMethod.POST,"/product/allproduct/*").authenticated()
			.antMatchers(HttpMethod.GET,"/product/allproduct/*").permitAll()
			.antMatchers(HttpMethod.GET,"/product/allproduct/name/*").authenticated()
			.antMatchers(HttpMethod.GET,"/product/allproduct/type/*").authenticated()
			.antMatchers(HttpMethod.GET,"/product/allproduct/category/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/product/allproduct/*").authenticated()
			.antMatchers(HttpMethod.DELETE,"/product/allproduct/*").permitAll()
			
			.antMatchers(HttpMethod.GET,"/category/categories").permitAll()
			.antMatchers(HttpMethod.GET,"/category/category/*").authenticated()
			.antMatchers(HttpMethod.POST,"/category/addCategory").authenticated()
			.antMatchers(HttpMethod.GET,"/category/category/byId/*").authenticated()
			.antMatchers(HttpMethod.GET,"/category/delete/*").permitAll()
			
			.antMatchers(HttpMethod.POST,"/review/addReview/*").authenticated()
			.antMatchers(HttpMethod.GET,"/review/reviews/*").authenticated()
			.antMatchers(HttpMethod.DELETE,"/review/delete/*").permitAll()
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
