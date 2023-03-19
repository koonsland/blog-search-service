package com.koon.blogsearchservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NaverServerException extends RuntimeException {
    private final NaverErrorResponse naverErrorResponse;
}
