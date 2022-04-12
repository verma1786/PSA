package com.state.psa.character.exception;

public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }
}