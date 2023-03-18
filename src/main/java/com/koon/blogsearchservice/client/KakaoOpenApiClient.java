package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchDTO;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.config.WebClientConfig;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOpenApiClient implements OpenApiClient {
    private final WebClientConfig webClientConfig;

    @Override
    public KakaoDTO getSearchBlog(SearchDTO searchDTO, Pageable pageable) {
        WebClient webClient = webClientConfig.kakaoWebClient();

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/search/blog")
                        .queryParam("query", searchDTO.getQuery())
                        .queryParam("sort", searchDTO.getSort())
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .build()
                )
                .retrieve()
                .bodyToMono(KakaoDTO.class)
                .block();
    }

}
