package com.koon.blogsearchservice.domain.event;

import com.koon.blogsearchservice.domain.service.PopularService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PopularEventHandler {

    private final PopularService popularService;

    @Async
    @EventListener
    public void onApplicationEvent(PopularEvent event) throws InterruptedException{
        log.info("[검색어 이벤트 핸들러] 검색어: {}", event.getName());
        popularService.plusPopularSearchWordCount(event.getName());
    }
}
