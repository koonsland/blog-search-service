package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.domain.model.Popular;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularResponse {
    private String name;
    private long count;

    public PopularResponse(Popular popular) {
        this.name = popular.getSearchWord();
        this.count = popular.getCount();
    }
}
