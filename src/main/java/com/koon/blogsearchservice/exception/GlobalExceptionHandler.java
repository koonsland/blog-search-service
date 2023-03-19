package com.koon.blogsearchservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity invalidInputExceptionHandler(BindException e) {
        log.info("{}", e.getMessage());
        return new ResponseEntity(
                commonErrorResponse(ErrorCode.INVALID_PARAMETER, HttpStatus.BAD_REQUEST),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {KakaoServerException.class})
    public ResponseEntity kakaoServerExceptionHandler(KakaoServerException e) {
        log.info("{}", e.getMessage());
        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        null,
                        e.getMessage()
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {NaverServerException.class})
    public ResponseEntity namverServerExceptionHandler(NaverServerException e) {
        log.info("{}", e.getMessage());
        return new ResponseEntity(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(),
                        e.getNaverErrorResponse().getErrorCode(),
                        e.getNaverErrorResponse().getErrorMessage()
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }


    private ErrorResponse commonErrorResponse(ErrorCode errorCode, HttpStatus httpStatus) {
        return new ErrorResponse(
                httpStatus.value(),
                httpStatus.BAD_REQUEST.name(),
                errorCode.getCode(),
                errorCode.getMessage()
        );
    }
}
