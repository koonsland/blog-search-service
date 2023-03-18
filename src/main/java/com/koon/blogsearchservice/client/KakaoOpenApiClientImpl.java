package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchDTO;
import com.koon.blogsearchservice.api.dto.kakao.KakaoDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoOpenApiClientImpl implements OpenAPIClient<KakaoDTO> {

    @Override
    public KakaoDTO search(SearchDTO searchDTO) {
        return null;
    }

}
