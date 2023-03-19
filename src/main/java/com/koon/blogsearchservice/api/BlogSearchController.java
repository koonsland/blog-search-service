package com.koon.blogsearchservice.api;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.SearchResponse;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    @GetMapping
    public ResponseEntity<SearchResponse> blogSearch(SearchRequest request) {
        SearchResponse searchResponse;
        PageRequest pageable = PageRequest.of(request.getPage(), request.getSize());

        try {
            KakaoDTO kakaoDTO = blogSearchService.kakaoBlogSearch(request.toServiceDTO(), pageable);
            searchResponse = new SearchResponse(kakaoDTO, pageable);
        } catch (RuntimeException e) {
            NaverDTO naverDTO = blogSearchService.naverBlogSearch(request.toServiceDTO(), pageable);
            searchResponse = new SearchResponse(naverDTO, pageable);
        }

        return ResponseEntity.ok(searchResponse);
    }
}
