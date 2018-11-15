package com.rajesh.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.springjpa.model.User;
import com.rajesh.springjpa.repo.UserJpaRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserJpaRepository userJpaRepo;
	
	@GetMapping(value="/all")
	public List<User> findAll(){
		return userJpaRepo.findAll();
	}
	
	@GetMapping(value="/{name}")
	public User findByName(@PathVariable final String name){
		return userJpaRepo.findByName(name);
	}
	
	@PostMapping(value="/load")
	public User load(@RequestBody final User user) {
		userJpaRepo.save(user);
		return userJpaRepo.findByName(user.getName());
	}
}
