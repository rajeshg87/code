package com.rajesh.springjpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.rajesh.springjpa.model.User;

@Component
public interface UserJpaRepository extends JpaRepository<User, Long>{

	User findByName(String name);

}
