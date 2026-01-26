package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ForbiddenException extends AppException {

    public ForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, message);
    }

    public ForbiddenException(String message, Map<String, Object> metadata) {
        super(ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, message, metadata);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, message, cause);
    }
}