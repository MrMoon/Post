package com.moon.squad.shared.exception;

import lombok.Getter;
import lombok.Setter;

public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String errorCode, errorMessage;

    public SequenceException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
