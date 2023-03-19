package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.config.KakaoConfig;
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
public class KakaoOpenApiClient implements OpenApiClient {
    private final WebClientConfig webClientConfig;
    private final KakaoConfig kakaoConfig;

    @Override
    public KakaoDTO getSearchBlog(SearchDTO searchDTO, Pageable pageable) {
        WebClient webClient = webClientConfig.webClient(kakaoConfig);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/search/blog")
                        .queryParam("query", searchDTO.getQuery())
                        .queryParam("sort", searchDTO.getSort().getName())
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .build()
                )
                .retrieve()
                .bodyToMono(KakaoDTO.class)
                .block();
    }

}
