package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus status;
    private final Map<String, Object> metadata;

    public AppException(ErrorCode errorCode, HttpStatus status, String message) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.metadata = null;
    }

    public AppException(ErrorCode errorCode, HttpStatus status, String message, Map<String, Object> metadata) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.metadata = metadata;
    }

    public AppException(ErrorCode errorCode, HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.status = status;
        this.metadata = null;
    }

    public AppException(ErrorCode errorCode, HttpStatus status, String message, Throwable cause, Map<String, Object> metadata) {
        super(message, cause);
        this.errorCode = errorCode;
        this.status = status;
        this.metadata = metadata;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}