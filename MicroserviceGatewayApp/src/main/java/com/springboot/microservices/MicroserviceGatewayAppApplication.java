package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceGatewayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayAppApplication.class, args);
	}

}
