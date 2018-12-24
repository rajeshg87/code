package com.rajesh.stock.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {
	String symbol;
	String name;
	String sector;	
}
