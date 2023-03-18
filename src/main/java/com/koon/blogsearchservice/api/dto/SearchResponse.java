package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private List<BlogDocumentResponse> documents;
    private int page;
    private int size;
    private int totalCount;

    public SearchResponse(KakaoDTO dto, Pageable pageable) {
        this.documents = dto.getDocuments().stream()
                .map(document -> new BlogDocumentResponse(document))
                .collect(Collectors.toList());
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.totalCount = dto.getMeta().getTotalCount();
    }

    public SearchResponse(NaverDTO dto, Pageable pageable) {
        this.documents = dto.getItems().stream()
                .map(document -> new BlogDocumentResponse(document))
                .collect(Collectors.toList());
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.totalCount = dto.getTotal();
    }
}
