package com.state.psa.community.exception;

public class InternalServiceError extends RuntimeException {
    public InternalServiceError(String message) {
        super(message);
    }
}
