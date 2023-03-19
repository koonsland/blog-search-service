package com.koon.blogsearchservice.api.dto;

import java.util.Arrays;

public enum Sort {
    ACCURACY("accuracy"),
    RECENCY("recency");

    private String name;

    Sort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Sort getName(String sortName) {
        return Arrays.stream(Sort.values())
                .filter(sort -> sort.equals(sortName))
                .findFirst()
                .orElse(ACCURACY);
    }
}
