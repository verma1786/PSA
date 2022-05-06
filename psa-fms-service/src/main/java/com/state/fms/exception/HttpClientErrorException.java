package com.state.fms.exception;

public class HttpClientErrorException extends RuntimeException{
    public HttpClientErrorException(String message) {
        super(message);
    }
}
