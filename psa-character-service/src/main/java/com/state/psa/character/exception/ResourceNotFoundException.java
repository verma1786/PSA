package com.state.psa.character.exception;

      /**
        * Throw an exception if declaration list not found
        */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
