package com.rajesh.cache.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.rajesh.cache.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	
	private static final String USER = "USER";

	private HashOperations<String, Integer, User> hashOperations;
	
	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void save(User user) {
		hashOperations.put(USER, user.getId(), user);
	}

	@Override
	public Map<Integer, User> findAll() {
		return hashOperations.entries(USER);
	}

	@Override
	public User findById(Integer id) {
		return hashOperations.get(USER, id);
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void delete(Integer id) {
		hashOperations.delete(USER, id);
	}

}
