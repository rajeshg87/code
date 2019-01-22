package com.rajesh.cache.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.cache.model.User;
import com.rajesh.cache.repo.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/add")
	public User add(@RequestBody User user) {
		userRepository.save(user);
		return userRepository.findById(user.getId());
	}
	
	@GetMapping("/all")
	public Map<Integer, User> findAll() {
		return userRepository.findAll();
	}
	
}
