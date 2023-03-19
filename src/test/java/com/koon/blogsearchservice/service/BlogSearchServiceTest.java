package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BlogSearchServiceTest {
    @Autowired
    private KakaoBlogSearchService blogSearchService;

    @Test
    @DisplayName("카카오 블로그 조회 성공")
    void successKakaoBlogSearch() {
        // given
        SearchDTO searchDTO = new SearchDTO("블로그", 1, 10, "accuracy");

        // when
        WebClientResponseDTO responseDTO = blogSearchService.blogSearch(searchDTO);

        // then
        assertEquals(responseDTO.getDocuments().size(), 10);
    }

}