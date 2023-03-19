package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;

public interface OpenApiClient {
    WebClientResponseDTO getSearchBlog(SearchDTO searchDTO);
}
