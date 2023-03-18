package com.koon.blogsearchservice.domain.model.repository;

import com.koon.blogsearchservice.domain.model.Popular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularRepository extends JpaRepository<Popular, Long> {
}
