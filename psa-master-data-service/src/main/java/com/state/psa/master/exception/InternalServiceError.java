package com.state.psa.master.exception;

public class InternalServiceError extends RuntimeException {
    public InternalServiceError(String message) {
        super(message);
    }
}
