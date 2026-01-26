package com.aniket.mirror.common.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ExternalServiceException extends AppException {

    public ExternalServiceException(String message) {
        super(ErrorCode.EXTERNAL_SERVICE_FAILURE, HttpStatus.BAD_GATEWAY, message);
    }

    public ExternalServiceException(String message, Map<String, Object> metadata) {
        super(ErrorCode.EXTERNAL_SERVICE_FAILURE, HttpStatus.BAD_GATEWAY, message, metadata);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(ErrorCode.EXTERNAL_SERVICE_FAILURE, HttpStatus.BAD_GATEWAY, message, cause);
    }
}