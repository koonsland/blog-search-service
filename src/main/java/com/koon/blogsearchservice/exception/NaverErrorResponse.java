package com.koon.blogsearchservice.exception;

import lombok.Data;

@Data
public class NaverErrorResponse {
    private String errorCode;
    private String errorMessage;
}
