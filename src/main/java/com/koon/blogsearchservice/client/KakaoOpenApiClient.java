package com.koon.blogsearchservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.config.KakaoConfig;
import com.koon.blogsearchservice.config.WebClientConfig;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import com.koon.blogsearchservice.exception.KakaoErrorResponse;
import com.koon.blogsearchservice.exception.KakaoServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOpenApiClient implements OpenApiClient {
    private final WebClientConfig webClientConfig;
    private final KakaoConfig kakaoConfig;

    @Override
    public WebClientResponseDTO getSearchBlog(SearchDTO searchDTO) {
        PageRequest pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize());

        KakaoDTO kakaoDTO = getBlogs(searchDTO, pageable);

        return new WebClientResponseDTO(kakaoDTO, pageable);
    }

    private KakaoDTO getBlogs(SearchDTO searchDTO, Pageable pageable) {
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
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            try {
                                log.error("{}", body);
                                KakaoErrorResponse kakaoErrorResponse = new ObjectMapper().readValue(body, KakaoErrorResponse.class);
                                return Mono.error(new KakaoServerException(kakaoErrorResponse));
                            } catch (JsonProcessingException e) {
                                return Mono.error(new RuntimeException());
                            }
                        })
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException())
                )
                .bodyToMono(KakaoDTO.class)
                .block();
    }

}
