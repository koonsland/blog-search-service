package com.koon.blogsearchservice.api.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koon.blogsearchservice.api.dto.naver.BlogItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDocumentDTO {
    @JsonProperty("blogname")
    private String blogname;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    public BlogDocumentDTO(BlogItemDTO item) {
        this.blogname = item.getBloggername();
        this.contents = item.getDescription();
        this.datetime = item.getPostdate();
        this.title = item.getTitle();
    }
}
