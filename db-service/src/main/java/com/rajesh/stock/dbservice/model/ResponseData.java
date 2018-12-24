package com.rajesh.stock.dbservice.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseData<T> {
	
	List<T> data;
	String message;

}
