package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.api.dto.SearchDTO;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.client.KakaoOpenApiClient;
import com.koon.blogsearchservice.client.NaverOpenApiClient;
import com.koon.blogsearchservice.domain.event.PopularEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final KakaoOpenApiClient kakaoClient;
    private final NaverOpenApiClient naverClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    public KakaoDTO kakaoBlogSearch(SearchDTO searchDTO, Pageable pageable) {
        // 카카오 Open API 조회
        KakaoDTO kakaoDTO = kakaoClient.getSearchBlog(searchDTO, pageable);

        // 검색어 저장을 위한 이벤트 발생
        applicationEventPublisher.publishEvent(new PopularEvent(searchDTO.getQuery()));

        return kakaoDTO;
    }

    public NaverDTO naverBlogSearch(SearchDTO searchDTO, Pageable pageable) {
        // 네이버 Open API 조회
        NaverDTO naverDTO = naverClient.getSearchBlog(searchDTO, pageable);

        // 검색어 저장을 위한 이벤트 발생
        applicationEventPublisher.publishEvent(new PopularEvent(searchDTO.getQuery()));

        return naverDTO;
    }
}
