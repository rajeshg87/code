package com.example.springbootsseclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Bean
    WebClient webClient() {
        return WebClient.create("http://localhost:8080/stream");
    }
}
