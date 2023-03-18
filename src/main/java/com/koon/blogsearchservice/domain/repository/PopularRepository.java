package com.koon.blogsearchservice.domain.repository;

import com.koon.blogsearchservice.domain.model.Popular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopularRepository extends JpaRepository<Popular, Long> {
    List<Popular> findTop10ByOrderByCountDesc();
}
