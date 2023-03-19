package com.koon.blogsearchservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidParameterException extends RuntimeException {
    private final ErrorCode errorCode;
}
