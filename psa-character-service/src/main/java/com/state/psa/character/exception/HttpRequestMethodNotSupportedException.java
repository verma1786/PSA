package com.state.psa.character.exception;

public class HttpRequestMethodNotSupportedException extends RuntimeException {
    public HttpRequestMethodNotSupportedException(final String message) {
        super(message);
    }
}
