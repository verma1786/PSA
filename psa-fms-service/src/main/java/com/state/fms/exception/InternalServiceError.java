package com.state.fms.exception;

public class InternalServiceError extends RuntimeException {
    public InternalServiceError(String message) {
        super(message);
    }
}
