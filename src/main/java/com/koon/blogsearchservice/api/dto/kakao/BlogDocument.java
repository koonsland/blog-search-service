package com.koon.blogsearchservice.api.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDocument {
    @JsonProperty("blogname")
    private String blogname;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;
}
