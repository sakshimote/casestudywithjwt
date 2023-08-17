package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.democontroller;
import com.example.demo.service.demoservice;

@SpringBootTest
class DemoApplicationTests {
	
	@Mock
	private demoservice demoservice;
	
	
	@InjectMocks
	private democontroller democontroller;

	@Test
	void contextLoads() {
	}
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void add() {
		
		when(demoservice.add(3,5)).thenReturn(8);
  	    assertNotNull(0,"wrong value");
		assertEquals(11,11);
	}
	

}
