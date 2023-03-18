package com.koon.blogsearchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.service.kakao.api-key}")
    private String kakaoApiKey;

    @Value("${app.service.kakao.base-url}")
    private String kakaoBaseUrl;

    @Value("${app.service.naver.api-key}")
    private String naverApiKey;

    @Value("${app.service.naver.secret}")
    private String naverSecret;

    @Value("${app.service.naver.base-url}")
    private String naverBaseUrl;

    public WebClient kakaoWebClient() {
        return WebClient
                .builder()
                .baseUrl(kakaoBaseUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, kakaoApiKey))
                .build();
    }

    public WebClient naverWebClient() {
        return WebClient
                .builder()
                .baseUrl(naverBaseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("X-Naver-Client-Id", naverApiKey);
                    httpHeaders.add("X-Naver-Client-Secret", naverSecret);
                })
                .build();
    }

}
