package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.config.NaverConfig;
import com.koon.blogsearchservice.config.WebClientConfig;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOpenApiClient implements OpenApiClient {

    private final WebClientConfig webClientConfig;
    private final NaverConfig naverConfig;

    @Override
    public NaverDTO getSearchBlog(SearchDTO searchDTO, Pageable pageable) {
        WebClient webClient = webClientConfig.webClient(naverConfig);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/blog.json")
                        .queryParam("query", searchDTO.getQuery())
                        .queryParam("sort", searchDTO.getSort().getName())
                        .queryParam("start", pageable.getPageNumber())
                        .queryParam("display", pageable.getPageSize())
                        .build()
                )
                .retrieve()
                .bodyToMono(NaverDTO.class)
                .block();
    }

}
