package com.state.psa.master.exception;

/**
 * Throw an exception if declaration list not found
 */
public class NotFoundError extends RuntimeException{
    public NotFoundError(String message) {
        super(message);
    }
}
