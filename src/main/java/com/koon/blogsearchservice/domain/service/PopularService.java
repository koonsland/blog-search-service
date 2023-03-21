package com.koon.blogsearchservice.domain.service;

import com.koon.blogsearchservice.domain.model.Popular;

import java.util.List;

public interface PopularService {
    List<Popular> getTop10Populars();

    Long plusPopularSearchWordCount(String name);
}
