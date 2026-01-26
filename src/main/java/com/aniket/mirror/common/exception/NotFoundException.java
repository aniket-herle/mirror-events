package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class NotFoundException extends AppException {

    public NotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Map<String, Object> metadata) {
        super(ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, message, metadata);
    }

    public NotFoundException(String message, Throwable cause) {
        super(ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND, message, cause);
    }
}