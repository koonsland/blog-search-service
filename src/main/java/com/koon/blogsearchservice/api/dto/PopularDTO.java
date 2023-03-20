package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.domain.model.Popular;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularDTO {
    private String word;
    private long count;

    public PopularDTO(Popular popular) {
        this.word = popular.getSearchWord();
        this.count = popular.getCount();
    }
}
