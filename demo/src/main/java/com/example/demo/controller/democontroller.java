package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.demoservice;

@RestController
public class democontroller {
	
	@Autowired
	private demoservice demoservice;
	
	
	@GetMapping("/add/{i}/{j}")
	public int add(@PathVariable("i") int i,@PathVariable("j") int j) {
		return demoservice.add(i, j);
	}
	
	
	

}
