package com.koon.blogsearchservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    public WebClient webClient(ClientConfig config) {
        return WebClient
                .builder()
                .baseUrl(config.getBaseUrl())
                .defaultHeaders(config.getDefaultHeaders())
                .build();
    }

}
