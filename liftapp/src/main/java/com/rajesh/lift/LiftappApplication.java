package com.rajesh.lift;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class LiftappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiftappApplication.class, args);
	}
	
	@Bean
	@Scope("singleton")
	public Lift intializeLift() {
		Lift lift=new Lift();
		lift.setFloor(0);
		Event[] event=new Event[5];
		event[0] = new Event(0, false);
		event[1] = new Event(1, false);
		event[2] = new Event(2, false);
		event[3] = new Event(3, false);
		event[4] = new Event(4, false);
		lift.setEvent(event);
		return lift;
	}
	
	@Bean
	public ExecutorService createThreadPool() {
		ExecutorService executorService=Executors.newFixedThreadPool(1);
		return executorService;
	}

}

