package com.gs.orbitshield.dto.response;

import java.time.Instant;

public class ErrorResponse {

    private String status;
    private ErrorDetail error;
    private Instant timestamp;

    public ErrorResponse(String code, String message, String path) {
        this.status = "error";
        this.error = new ErrorDetail(code, message, path);
        this.timestamp = Instant.now();
    }

    public static ErrorResponse of(String code, String message, String path) {
        return new ErrorResponse(code, message, path);
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorDetail getError() {
        return error;
    }

    public void setError(ErrorDetail error) {
        this.error = error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static class ErrorDetail {
        private String code;
        private String message;
        private String path;

        public ErrorDetail(String code, String message, String path) {
            this.code = code;
            this.message = message;
            this.path = path;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }
    }
}

