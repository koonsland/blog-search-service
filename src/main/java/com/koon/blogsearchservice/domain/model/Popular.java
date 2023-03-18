package com.koon.blogsearchservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "popular")
@Getter
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
}
