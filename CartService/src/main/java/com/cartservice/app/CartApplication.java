package com.cartservice.app;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}
	
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/cart/**"))
				.apis(RequestHandlerSelectors.basePackage("com.cartservice.app"))
				.build()
				.apiInfo(apiDetails());
		
	}

	private ApiInfo apiDetails() {
		// TODO Auto-generated method stub
		return new ApiInfo(
				"Cart Service",
				"Microservice for Shopping Cart System",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Sakshi Mote",
						"http://javabrains.com", "sakshi@gmail.com"),
				"API license",
				"http://javabrains.com",
				Collections.emptyList());
			
	}
	

	@Bean
	   public static RestTemplate restTemplate() {
	      return new RestTemplate();
	   }

}
