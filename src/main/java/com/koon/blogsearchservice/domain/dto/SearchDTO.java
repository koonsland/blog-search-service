package com.koon.blogsearchservice.domain.dto;

import com.koon.blogsearchservice.api.dto.Sort;
import lombok.Data;

@Data
public class SearchDTO {
    private String query;
    private int page = 1;
    private int size = 10;
    private Sort sort;

    public SearchDTO(String query, int page, int size, String sort) {
        this.query = query;
        this.page = page;
        this.size = size;
        this.sort = Sort.getName(sort);
    }
}
