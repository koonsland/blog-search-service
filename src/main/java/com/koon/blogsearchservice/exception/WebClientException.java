package com.koon.blogsearchservice.exception;

import com.koon.blogsearchservice.domain.dto.WebClientError;

public class WebClientException extends RuntimeException {

    public WebClientException(String message) {
        super(message);
    }
}
