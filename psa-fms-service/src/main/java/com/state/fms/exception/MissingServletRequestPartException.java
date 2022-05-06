package com.state.fms.exception;

public class MissingServletRequestPartException extends RuntimeException{
    public MissingServletRequestPartException(String message) {
        super(message);
    }
}
