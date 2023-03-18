package com.koon.blogsearchservice.api.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogMeta {
    @JsonProperty("is_end")
    private Boolean isEnd;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("total_count")
    private int totalCount;
}
