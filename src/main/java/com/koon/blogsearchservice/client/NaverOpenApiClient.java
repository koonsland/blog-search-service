package com.koon.blogsearchservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.config.NaverConfig;
import com.koon.blogsearchservice.config.WebClientConfig;
import com.koon.blogsearchservice.domain.dto.NaverSearchDTO;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientError;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import com.koon.blogsearchservice.exception.KakaoServerException;
import com.koon.blogsearchservice.exception.NaverErrorResponse;
import com.koon.blogsearchservice.exception.NaverServerException;
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
public class NaverOpenApiClient implements OpenApiClient {

    private final WebClientConfig webClientConfig;
    private final NaverConfig naverConfig;

    @Override
    public WebClientResponseDTO getSearchBlog(SearchDTO searchDTO) {
        NaverSearchDTO naverSearchDTO = new NaverSearchDTO(
                searchDTO.getQuery(),
                searchDTO.getPage(),
                searchDTO.getSize(),
                searchDTO.getSort()
        );

        PageRequest pageable = PageRequest.of(naverSearchDTO.getPage(), searchDTO.getSize());

        NaverDTO naverDTO = getBlogs(naverSearchDTO, pageable);

        return new WebClientResponseDTO(naverDTO, pageable);

    }

    private NaverDTO getBlogs(NaverSearchDTO searchDTO, Pageable pageable) {
        WebClient webClient = webClientConfig.webClient(naverConfig);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/blog.json")
                        .queryParam("query", searchDTO.getQuery())
                        .queryParam("sort", searchDTO.getSort())
                        .queryParam("start", pageable.getOffset())
                        .queryParam("display", pageable.getPageSize())
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            try {
                                log.error("{}", body);
                                NaverErrorResponse naverErrorResponse = new ObjectMapper().readValue(body, NaverErrorResponse.class);
                                return Mono.error(new NaverServerException(naverErrorResponse));
                            } catch (JsonProcessingException e) {
                                return Mono.error(new RuntimeException());
                            }
                        })
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException())
                )
                .bodyToMono(NaverDTO.class)
                .block();
    }
}
