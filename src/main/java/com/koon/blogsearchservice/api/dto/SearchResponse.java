package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public SearchResponse(WebClientResponseDTO dto) {
        this.documents = dto.getDocuments()
                .stream()
                .map(document -> new BlogDocumentResponse(document))
                .collect(Collectors.toList());
        this.page = dto.getPage();
        this.size = dto.getSize();
        this.totalCount = dto.getTotalCount();
    }
}
