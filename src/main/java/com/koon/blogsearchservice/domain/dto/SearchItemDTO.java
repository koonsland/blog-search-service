package com.koon.blogsearchservice.domain.dto;

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
public class SearchItemDTO {
    private String blogname;

    private String contents;

    private LocalDateTime datetime;

    private String thumbnail;

    private String title;

    private String url;

    public SearchItemDTO(BlogDocumentDTO document) {
        this.blogname = document.getBlogname();
        this.contents = document.getContents();
        this.datetime = LocalDateTimeUtil.toLocalDateTime(document.getDatetime());
        this.thumbnail = document.getThumbnail();
        this.title = document.getTitle();
        this.url = document.getUrl();
    }

    public SearchItemDTO(BlogItemDTO item) {
        this.blogname = item.getBloggername();
        this.contents = item.getDescription();
        this.datetime = LocalDateTimeUtil.toLocalDateTime(item.getPostdate());
        this.thumbnail = null;
        this.title = item.getTitle();
        this.url = item.getLink();
    }
}
