package com.example.finance_server.Model;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String error;
    private String errorCode;
    private LocalDateTime timestamp;

    public ErrorResponse(String error, String errorCode) {
        this.error = error;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
