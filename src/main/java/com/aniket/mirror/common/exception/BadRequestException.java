package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BadRequestException extends AppException {

    public BadRequestException(String message) {
        super(ErrorCode.INVALID_INPUT, HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(String message, Map<String, Object> metadata) {
        super(ErrorCode.INVALID_INPUT, HttpStatus.BAD_REQUEST, message, metadata);
    }

    public BadRequestException(String message, Throwable cause) {
        super(ErrorCode.INVALID_INPUT, HttpStatus.BAD_REQUEST, message, cause);
    }
}