package com.rajesh.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.mongo.model.Hotel;
import com.rajesh.mongo.repo.HotelRepository;

@RestController
@RequestMapping("/rest/hotels")
public class HotelController {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("/all")
	public List<Hotel> getHotels(){
		return hotelRepository.findAll();
	}
	
	@PutMapping("/add")
	public List<Hotel> addHotel(@RequestBody Hotel hotel){
		hotelRepository.insert(hotel);
		return hotelRepository.findAll();
	}
	
	@PostMapping("/update")
	public List<Hotel> updateHotel(@RequestBody Hotel hotel){
		hotelRepository.save(hotel);
		return hotelRepository.findAll();
	}
	
	@DeleteMapping("/delete")
	public List<Hotel> deleteHotel(@PathVariable String id){
		hotelRepository.deleteById(id);
		return hotelRepository.findAll();
	}
	
	
}
