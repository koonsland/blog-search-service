package com.koon.blogsearchservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER("C0001", "파라미터를 잘못 입력했어요");

    private final String code;
    private final String message;

}
