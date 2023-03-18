package com.koon.blogsearchservice.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "popular")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Popular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popular_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private long count;
}
