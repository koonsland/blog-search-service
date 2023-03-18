package com.koon.blogsearchservice.api.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoDTO {
    private List<BlogDocument> documents;
    private BlogMeta meta;
}
