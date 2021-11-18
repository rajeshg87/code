package com.example.springbootsseclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class MessageConsumerService {

    private final WebClient webCLient;

    private ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {
    };

    public Flux<ServerSentEvent<String>> consume(){
        return webCLient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(type);
    }
}
