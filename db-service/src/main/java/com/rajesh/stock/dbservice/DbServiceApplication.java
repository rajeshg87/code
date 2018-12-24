package com.rajesh.stock.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.rajesh.stock.dbservice.repo")
@SpringBootApplication
public class DbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}
}
