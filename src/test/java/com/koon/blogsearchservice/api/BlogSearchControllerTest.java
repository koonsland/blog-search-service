package com.koon.blogsearchservice.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class BlogSearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("블로그 검색 성공")
    void successBlogSearch() throws Exception {
        // given
        String url = "/search";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "카카오");

        // when & then
        mockMvc
                .perform(
                        get("/search")
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(10));

    }

    @Test
    @DisplayName("블로그 검색 실패(쿼리 없음)")
    void failedBlogSearch() throws Exception {
        // given
        String url = "/search";

        // when & then
        mockMvc
                .perform(
                        get("/search")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.code").value("MissingParameter"));
    }

}