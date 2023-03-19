package com.koon.blogsearchservice.service;

import com.koon.blogsearchservice.domain.event.PopularEvent;
import com.koon.blogsearchservice.domain.model.Popular;
import com.koon.blogsearchservice.domain.repository.PopularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PopularServiceImpl implements PopularService {

    private final PopularRepository popularRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public List<Popular> getTop10Populars() {
        return popularRepository.findTop10ByOrderByCountDesc();
    }

    @Override
    @Transactional
    public Long plusPopularNameCount(String name) {
        // 화이트 스페이스 제거
        String popularStr = trimString(name);

        // 기존 검색어 조회
        Popular findPopular = popularRepository.findByName(popularStr);
        if (findPopular != null) {
            findPopular.plusCount();
            return findPopular.getId();
        }

        // 저장
        Popular popular = Popular.createPopular(popularStr);
        try {
            popularRepository.save(popular);
        } catch (RuntimeException e) {
            applicationEventPublisher.publishEvent(new PopularEvent(popularStr));
            throw new RuntimeException("저장 중 충돌. 중복 문제로 이벤트 재발생");
        }
        return popular.getId();
    }

    private String trimString(String name) {
        return name.trim();
    }
}
