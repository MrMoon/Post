package com.moon.squad.configuration.security.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
