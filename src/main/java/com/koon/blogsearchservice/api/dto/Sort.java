package com.koon.blogsearchservice.api.dto;

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
}
