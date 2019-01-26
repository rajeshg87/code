package com.rajesh.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.mongo.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>{

}
