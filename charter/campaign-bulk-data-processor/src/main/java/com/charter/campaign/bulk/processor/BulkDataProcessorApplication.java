package com.charter.campaign.bulk.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BulkDataProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkDataProcessorApplication.class, args);
	}

}
