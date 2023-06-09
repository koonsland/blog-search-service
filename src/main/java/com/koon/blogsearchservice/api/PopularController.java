package com.koon.blogsearchservice.api;

import com.koon.blogsearchservice.api.dto.PopularDTO;
import com.koon.blogsearchservice.api.dto.PopularResponse;
import com.koon.blogsearchservice.domain.model.Popular;
import com.koon.blogsearchservice.domain.service.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/populars")
@RequiredArgsConstructor
public class PopularController {
    private final PopularService popularService;

    @GetMapping
    public ResponseEntity<PopularResponse> getPopulars() {
        List<Popular> populars = popularService.getTop10Populars();
        List<PopularDTO> response = populars
                .stream()
                .map(popular -> new PopularDTO(popular))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new PopularResponse(response));
    }
}
