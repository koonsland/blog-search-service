package com.koon.blogsearchservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoServerException extends RuntimeException {
    private final KakaoErrorResponse kakaoErrorResponse;
}
