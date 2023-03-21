package com.koon.blogsearchservice.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PopularControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    @DisplayName("검색어 상위 10 조회 성공")
    void successSearchWordTop10() throws Exception {
        // given
        String searchWord = "카카오";
        successBlogSearch(searchWord);
        String url = "/populars";

        // when & then
        mockMvc
                .perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].word").value("카카오"))
                .andExpect(jsonPath("$.items[0].count").value(100));

    }

    void successBlogSearch(String searchWord) throws Exception {
        // given
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 100;
        ExecutorService service = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);

        String url = "/search";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", searchWord);

        // when & then
        // when
        for (int i = 0; i < numberOfExcute; i++) {
            service.execute(() -> {
                try {
                    postMockMvc(url, params);
                    successCount.getAndIncrement();
                    log.info("성공");
                } catch (Exception e) {
                    log.error("[테스트코드]: {}", e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();
        Thread.sleep(1000);
    }

    void postMockMvc(String url, MultiValueMap params) throws Exception {
        mockMvc
                .perform(
                        get(url)
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON)
                );
    }
}