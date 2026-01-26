package com.aniket.mirror.common;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String traceId;
    private String errorCode;
    private List<ValidationError> details;

    // Constructors, getters, setters

    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(int status, String error, String message, String path, String traceId, String errorCode, List<ValidationError> details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.traceId = traceId;
        this.errorCode = errorCode;
        this.details = details;
    }

    // Getters and setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<ValidationError> getDetails() {
        return details;
    }

    public void setDetails(List<ValidationError> details) {
        this.details = details;
    }
}