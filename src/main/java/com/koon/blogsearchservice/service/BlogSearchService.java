package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.client.OpenApiClient;
import com.koon.blogsearchservice.domain.dto.SearchDTO;
import com.koon.blogsearchservice.domain.dto.WebClientResponseDTO;
import com.koon.blogsearchservice.domain.event.PopularEvent;
import org.springframework.context.ApplicationEventPublisher;

public interface BlogSearchService {
    default WebClientResponseDTO blogSearch(SearchDTO searchDTO) {
        // 클라이언트 통신
        WebClientResponseDTO searchBlog = getApiClient().getSearchBlog(searchDTO);

        // 이벤트 핸들러
        getApplicationEventPublisher().publishEvent(new PopularEvent(searchDTO.getQuery()));

        return searchBlog;
    }

    OpenApiClient getApiClient();

    ApplicationEventPublisher getApplicationEventPublisher();
}
