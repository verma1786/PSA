package com.state.psa.character.exception;


public class InternalServiceError extends RuntimeException {
    public InternalServiceError(final String message) {
        super(message);
    }
}
