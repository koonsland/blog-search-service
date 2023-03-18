package com.koon.blogsearchservice.client;

import com.koon.blogsearchservice.api.dto.SearchDTO;

interface OpenAPIClient<T> {
    T search(SearchDTO searchDTO);
}
