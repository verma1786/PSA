package com.state.psa.community.exception;

/**
 * Throw an exception if declaration list not found
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
