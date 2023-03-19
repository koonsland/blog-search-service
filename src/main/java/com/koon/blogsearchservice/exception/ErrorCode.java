package com.koon.blogsearchservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER("C0001", "파라미터를 잘못 입력했어요"),
    KAKAO_SERVER_EXCEPTION("K0001", "카카오 서버와 통신할 수 없어요"),
    NAVER_SERVER_EXCEPTION("N0001", "네이버 서버와 통신할 수 없어요");

    private final String code;
    private final String message;

}
