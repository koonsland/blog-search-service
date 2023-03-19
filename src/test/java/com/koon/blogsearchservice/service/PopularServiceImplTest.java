package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.domain.model.Popular;
import com.koon.blogsearchservice.domain.repository.PopularRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PopularServiceImplTest {
    @Autowired
    private PopularService popularService;

    @Autowired
    private PopularRepository popularRepository;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("인기 검색어 저장")
    void popularWordSave() throws InterruptedException {
        // given
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 1000;
        ExecutorService service = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);
        String name = "카카오 뱅크";

        // when
        for (int i = 0; i < numberOfExcute; i++) {
            service.execute(() -> {
                try {
                    popularService.plusPopularNameCount(name);
                    successCount.getAndIncrement();
                    log.info("성공");
                } catch (Exception e) {
                    log.error("[테스트코드]: {}", e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();

        Popular findPopular = popularRepository.findByName(name);

        // then
        assertEquals(findPopular.getCount(), numberOfExcute);
    }
}