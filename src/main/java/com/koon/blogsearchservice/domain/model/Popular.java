package com.koon.blogsearchservice.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "popular")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Popular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popular_id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "count", nullable = false)
    private long count;

    private Popular(String name) {
        this.name = name;
        this.count = 1;
    }

    // 생성 메서드
    public static Popular createPopular(String name) {
        return new Popular(name);
    }

    // 비지니스 메서드
    public void plusCount() {
        this.count++;
    }
}
