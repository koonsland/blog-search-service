package com.koon.blogsearchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

@Configuration
public class NaverConfig implements ClientConfig {
    @Value("${app.service.naver.api-key}")
    private String naverApiKey;

    @Value("${app.service.naver.secret}")
    private String naverSecret;

    @Value("${app.service.naver.base-url}")
    private String naverBaseUrl;

    @Override
    public String getBaseUrl() {
        return this.naverBaseUrl;
    }

    @Override
    public Consumer<HttpHeaders> getDefaultHeaders() {
        Consumer<HttpHeaders> consumer = httpHeaders -> {
            httpHeaders.add("X-Naver-Client-Id", naverApiKey);
            httpHeaders.add("X-Naver-Client-Secret", naverSecret);
        };
        return consumer;
    }

}
