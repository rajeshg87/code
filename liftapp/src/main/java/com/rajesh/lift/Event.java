package com.rajesh.lift;

import org.springframework.context.annotation.ComponentScan;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ComponentScan(basePackages = "com.rajesh.lift")
@AllArgsConstructor
public class Event {
	int floor;
	boolean process;
}
