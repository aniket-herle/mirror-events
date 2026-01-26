package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class InternalServerException extends AppException {

    public InternalServerException(String message) {
        super(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(String message, Map<String, Object> metadata) {
        super(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, message, metadata);
    }

    public InternalServerException(String message, Throwable cause) {
        super(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }
}