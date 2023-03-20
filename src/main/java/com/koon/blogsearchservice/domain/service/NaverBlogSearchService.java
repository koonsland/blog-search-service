package com.koon.blogsearchservice.domain.service;

import com.koon.blogsearchservice.client.NaverOpenApiClient;
import com.koon.blogsearchservice.client.OpenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverBlogSearchService implements BlogSearchService {
    private final NaverOpenApiClient apiClient;
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
