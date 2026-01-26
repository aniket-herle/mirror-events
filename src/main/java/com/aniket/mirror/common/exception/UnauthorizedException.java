package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UnauthorizedException extends AppException {

    public UnauthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, Map<String, Object> metadata) {
        super(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, message, metadata);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, message, cause);
    }
}