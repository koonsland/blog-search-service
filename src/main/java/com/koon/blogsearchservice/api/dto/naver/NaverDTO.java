package com.koon.blogsearchservice.api.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaverDTO {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BlogItemDTO> items;
}
