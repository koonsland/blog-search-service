package com.koon.blogsearchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

@Configuration
public class KakaoConfig implements ClientConfig {
    @Value("${app.service.kakao.api-key}")
    private String kakaoApiKey;

    @Value("${app.service.kakao.base-url}")
    private String kakaoBaseUrl;

    @Override
    public String getBaseUrl() {
        return this.kakaoBaseUrl;
    }

    @Override
    public Consumer<HttpHeaders> getDefaultHeaders() {
        Consumer<HttpHeaders> consumer = httpHeaders -> httpHeaders.add(org.springframework.http.HttpHeaders.AUTHORIZATION, kakaoApiKey);
        return consumer;
    }

}
