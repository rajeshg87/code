package com.rajesh.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.hasScope('read')")
	public String greet() {
		return "Hello from Secured Endpoint";
	}
}
