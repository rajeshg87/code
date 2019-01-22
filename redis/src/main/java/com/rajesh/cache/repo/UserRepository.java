package com.rajesh.cache.repo;

import java.util.Map;

import com.rajesh.cache.model.User;

public interface UserRepository {
	void save(User user);
	Map<Integer,User> findAll();
	User findById(Integer id);
	void update(User user);
	void delete(Integer id);
}
