package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.domain.model.Popular;
import com.koon.blogsearchservice.domain.repository.PopularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularServiceImpl implements PopularService {

    private final PopularRepository popularRepository;

    @Override
    public List<Popular> getTop10Populars() {
        return popularRepository.findTop10ByOrderByCountDesc();
    }
}
