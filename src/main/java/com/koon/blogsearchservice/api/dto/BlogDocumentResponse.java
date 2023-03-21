package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.domain.dto.SearchItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDocumentResponse {
    private String blogname;

    private String contents;

    private LocalDateTime datetime;

    private String thumbnail;

    private String title;

    private String url;

    public BlogDocumentResponse(SearchItemDTO document) {
        this.blogname = document.getBlogname();
        this.contents = document.getContents();
        this.datetime = document.getDatetime();
        this.thumbnail = document.getThumbnail();
        this.title = document.getTitle();
        this.url = document.getUrl();
    }
}
