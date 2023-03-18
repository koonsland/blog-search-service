package com.koon.blogsearchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

public class KakaoWebClientConfigImpl implements WebClientConfig {

    @Value("${app.service.kakao.api-key}")
    private String apiKey;

    @Value("${app.service.kakao.base-url}")
    private String baseUrl;

    @Override
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public Consumer<HttpHeaders> getDefaultHeader() {
        Consumer<HttpHeaders> consumer = httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, this.apiKey);
        return consumer;
    }
}
