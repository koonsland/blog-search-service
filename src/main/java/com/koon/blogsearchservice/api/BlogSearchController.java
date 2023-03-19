package com.koon.blogsearchservice.api;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.SearchResponse;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import com.koon.blogsearchservice.exception.KakaoServerException;
import com.koon.blogsearchservice.service.KakaoBlogSearchService;
import com.koon.blogsearchservice.service.NaverBlogSearchService;
import jakarta.validation.Valid;
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
@Slf4j
public class BlogSearchController {
    private final KakaoBlogSearchService kakaoBlogSearchService;
    private final NaverBlogSearchService naverBlogSearchService;

    @GetMapping
    public ResponseEntity<SearchResponse> blogSearch(@Valid SearchRequest request) {
        SearchResponse searchResponse;

        try {
            searchResponse = new SearchResponse(kakaoBlogSearchService.blogSearch(request.toServiceDTO()));
        } catch (KakaoServerException e) {
            throw new KakaoServerException(e.getMessage());
        } catch (RuntimeException e) {
            searchResponse = new SearchResponse(naverBlogSearchService.blogSearch(request.toServiceDTO()));
        }

        return ResponseEntity.ok(searchResponse);
    }
}
