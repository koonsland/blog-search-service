package com.koon.blogsearchservice.domain.dto;

import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WebClientResponseDTO {
    private List<SearchItemDTO> documents;
    private int page;
    private int size;
    private int totalCount;


    public WebClientResponseDTO(KakaoDTO dto, Pageable pageable) {
        this.documents = dto.getDocuments().stream()
                .map(document -> new SearchItemDTO(document))
                .collect(Collectors.toList());
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.totalCount = dto.getMeta().getTotalCount();
    }

    public WebClientResponseDTO(NaverDTO dto, PageRequest pageable) {
        this.documents = dto.getItems().stream()
                .map(item -> new SearchItemDTO(item))
                .collect(Collectors.toList());
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.totalCount = dto.getTotal();
    }
}
