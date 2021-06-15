package com.tutorial.springTutorial.contoller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitTestController {
	
	
	@RequestMapping("/test")
	public String greeting() {
		return "Hello, World";
	}

}
