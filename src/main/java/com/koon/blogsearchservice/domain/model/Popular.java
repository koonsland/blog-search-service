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

    @Column(name = "search_word", length = 255, nullable = false, unique = true)
    private String searchWord;

    @Column(name = "count", nullable = false)
    private long count;

    private Popular(String searchWord) {
        this.searchWord = searchWord;
        this.count = 1;
    }

    // 생성 메서드
    public static Popular createPopular(String searchWord) {
        return new Popular(searchWord);
    }

    // 비지니스 메서드
    public void plusCount() {
        this.count++;
    }
}
