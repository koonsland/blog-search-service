package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.api.dto.SearchDTO;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.client.KakaoOpenApiClient;
import com.koon.blogsearchservice.client.NaverOpenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final KakaoOpenApiClient kakaoClient;
    private final NaverOpenApiClient naverClient;

    public KakaoDTO kakaoBlogSearch(SearchDTO searchDTO, Pageable pageable) {
        return kakaoClient.getSearchBlog(searchDTO, pageable);
    }

    public NaverDTO naverBlogSearch(SearchDTO searchDTO, Pageable pageable) {
        return naverClient.getSearchBlog(searchDTO, pageable);
    }
}
