package com.koon.blogsearchservice.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchDTO {
    @NotBlank
    private String query;

    @Min(1)
    @Max(50)
    private int page = 1;

    @Min(1)
    @Max(50)
    private int size = 10;

    private String sort;
}
