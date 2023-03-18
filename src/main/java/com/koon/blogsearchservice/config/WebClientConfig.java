package com.koon.blogsearchservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

public interface WebClientConfig {

    @Bean
    default WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(getBaseUrl())
                .defaultHeaders(getDefaultHeader())
                .build();
    }

    String getBaseUrl();

    Consumer<HttpHeaders> getDefaultHeader();

}
