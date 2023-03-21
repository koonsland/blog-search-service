package com.koon.blogsearchservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PopularResponse {
    private List<PopularDTO> items;
}
