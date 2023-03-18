package com.koon.blogsearchservice.api;

import com.koon.blogsearchservice.api.dto.SearchDTO;
import com.koon.blogsearchservice.api.dto.SearchResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Validated
@Slf4j
public class BlogSearchController {
    @GetMapping
    public ResponseEntity<SearchResultDTO> blogSearch(SearchDTO searchDTO) {
        log.info("{}", searchDTO);
        return ResponseEntity.ok(new SearchResultDTO());
    }
}
