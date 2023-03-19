package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import org.springframework.data.domain.Pageable;

public interface OpenApiClient {
    WebClientResponseDTO getSearchBlog(SearchDTO searchDTO);
}
