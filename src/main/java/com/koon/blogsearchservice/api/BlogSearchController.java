package com.koon.blogsearchservice.api;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.api.dto.SearchResponse;
import com.koon.blogsearchservice.exception.KakaoServerException;
import com.koon.blogsearchservice.domain.service.KakaoBlogSearchService;
import com.koon.blogsearchservice.domain.service.NaverBlogSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<SearchResponse> blogSearch(SearchRequest request) {
        SearchResponse searchResponse;

        try {
            searchResponse = new SearchResponse(kakaoBlogSearchService.blogSearch(request.toServiceDTO()));
        } catch (KakaoServerException e) {
            throw e;
        } catch (RuntimeException e) {
            searchResponse = new SearchResponse(naverBlogSearchService.blogSearch(request.toServiceDTO()));
        }

        return ResponseEntity.ok(searchResponse);
    }
}
