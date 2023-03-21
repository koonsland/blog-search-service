package com.koon.blogsearchservice.domain.dto;

import com.koon.blogsearchservice.api.dto.Sort;
import lombok.Data;

@Data
public class NaverSearchDTO {
    private String query;
    private int page = 1;
    private int offset = 1;
    private int size = 10;
    private String sort;

    public NaverSearchDTO(String query, int page, int size, Sort sort) {
        this.query = query;
        this.page = page;
        this.offset = (page - 1) * size + 1;
        this.size = size;
        this.sort = Sort.getNaverName(sort);
    }
}
