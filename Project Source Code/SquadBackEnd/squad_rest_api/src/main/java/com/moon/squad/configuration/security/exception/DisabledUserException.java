package com.moon.squad.configuration.security.exception;

public class DisabledUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DisabledUserException(String message) {
        super(message);
    }
}
