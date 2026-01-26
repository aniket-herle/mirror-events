package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ConflictException extends AppException {

    public ConflictException(String message) {
        super(ErrorCode.CONFLICT, HttpStatus.CONFLICT, message);
    }

    public ConflictException(String message, Map<String, Object> metadata) {
        super(ErrorCode.CONFLICT, HttpStatus.CONFLICT, message, metadata);
    }

    public ConflictException(String message, Throwable cause) {
        super(ErrorCode.CONFLICT, HttpStatus.CONFLICT, message, cause);
    }
}