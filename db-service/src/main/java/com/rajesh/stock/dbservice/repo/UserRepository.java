package com.rajesh.stock.dbservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.stock.dbservice.model.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Integer> {
    List<UserDetail> findByUserName(String username);
}