package com.state.psa.community.exception;

public class HttpClientErrorException extends RuntimeException{
    public HttpClientErrorException(String message) {
        super(message);
    }
}
