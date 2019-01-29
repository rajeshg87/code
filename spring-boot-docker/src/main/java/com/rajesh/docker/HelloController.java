package com.rajesh.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloController {
	
	@GetMapping
	public String hello() {
		return "Hi Rajesh, Welcome to Docker !";
	}
}
