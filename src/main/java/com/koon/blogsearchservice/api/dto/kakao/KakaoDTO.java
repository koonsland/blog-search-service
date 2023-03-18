package com.koon.blogsearchservice.api.dto.kakao;

import com.koon.blogsearchservice.api.dto.naver.NaverDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoDTO {
    private List<BlogDocumentDTO> documents;
    private BlogMeta meta;
}
