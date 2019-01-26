package com.rajesh.mongo.model;

import lombok.Data;

@Data
public class Review {
	private String userName;
	private Integer rating;
	private boolean approved;
}
