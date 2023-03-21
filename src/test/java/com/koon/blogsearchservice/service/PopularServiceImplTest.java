package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.domain.model.Popular;
import com.koon.blogsearchservice.domain.repository.PopularRepository;
import com.koon.blogsearchservice.domain.service.PopularService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
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
    @DisplayName("인기 검색어 저장 성공")
    void savePopularWord() throws InterruptedException {
        String searchWord = "인기 검색어 저장 성공";
        Long savedId = popularService.plusPopularSearchWordCount(searchWord);

        Popular findPopular = popularRepository.findById(savedId).get();

        assertEquals(findPopular.getSearchWord(), searchWord);
    }


    @Test
    @DisplayName("인기 검색어 저장 (동시성 이슈)")
    void savePopularWordMultiThread() throws InterruptedException {
        // given
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 1000;
        ExecutorService service = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);
        String searchWord = "이슈";

        // when
        for (int i = 0; i < numberOfExcute; i++) {
            service.execute(() -> {
                try {
                    popularService.plusPopularSearchWordCount(searchWord);
                    successCount.getAndIncrement();
                    log.info("성공");
                } catch (Exception e) {
                    log.error("[테스트코드]: {}", e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();

        Popular findPopular = popularRepository.findBySearchWord(searchWord);

        // then
        assertEquals(findPopular.getCount(), numberOfExcute);
    }

    @Test
    @DisplayName("검색어 top 10 조회 성공")
    void successPopularSearchWord() {
        // given
        savePopularSearchWords();

        // when
        List<Popular> top10Populars = popularService.getTop10Populars();

        // then
        assertEquals(top10Populars.size(), 10);
    }

    private void savePopularSearchWords() {
        String word = "검색어";
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int rand = random.nextInt() % 10;
            popularService.plusPopularSearchWordCount(word + rand);
        }
    }
}