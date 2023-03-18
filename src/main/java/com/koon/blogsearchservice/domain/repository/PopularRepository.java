package com.koon.blogsearchservice.domain.repository;

import com.koon.blogsearchservice.domain.model.Popular;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface PopularRepository extends JpaRepository<Popular, Long> {
    List<Popular> findTop10ByOrderByCountDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Popular findByName(String name);
}
