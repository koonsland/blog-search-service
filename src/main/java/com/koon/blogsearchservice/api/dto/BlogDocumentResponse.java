package com.koon.blogsearchservice.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koon.blogsearchservice.api.dto.kakao.BlogDocumentDTO;
import com.koon.blogsearchservice.api.dto.naver.BlogItemDTO;
import com.koon.blogsearchservice.utils.LocalDateTimeUtil;
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

    public BlogDocumentResponse(BlogDocumentDTO dto) {
        this.blogname = dto.getBlogname();
        this.contents = dto.getContents();
        this.datetime = LocalDateTimeUtil.toLocalDateTime(dto.getDatetime());
        this.thumbnail = dto.getThumbnail();
        this.title = dto.getTitle();
        this.url = dto.getUrl();
    }

    public BlogDocumentResponse(BlogItemDTO dto) {
        this.blogname = dto.getBloggername();
        this.contents = dto.getDescription();
        this.datetime = LocalDateTimeUtil.toLocalDateTime(dto.getPostdate());
        this.thumbnail = null;
        this.title = dto.getTitle();
        this.url = dto.getLink();
    }
}
