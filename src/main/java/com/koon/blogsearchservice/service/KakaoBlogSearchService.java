package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.client.KakaoOpenApiClient;
import com.koon.blogsearchservice.client.OpenApiClient;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoBlogSearchService implements BlogSearchService {
    private final KakaoOpenApiClient apiClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public OpenApiClient getApiClient() {
        return apiClient;
    }

    @Override
    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }
}
