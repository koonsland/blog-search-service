package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchRequest;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import org.springframework.data.domain.Pageable;

public interface OpenApiClient<T> {
    T getSearchBlog(SearchDTO searchDTO, Pageable pageable);
}
