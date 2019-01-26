package com.rajesh.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rajesh.mongo")
public class SpringMongodbSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbSampleApplication.class, args);
	}

}

