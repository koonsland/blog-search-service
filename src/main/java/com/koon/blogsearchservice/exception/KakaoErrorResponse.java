package com.koon.blogsearchservice.exception;

import lombok.Data;

@Data
public class KakaoErrorResponse {
    private String errorType;
    private String message;
}
