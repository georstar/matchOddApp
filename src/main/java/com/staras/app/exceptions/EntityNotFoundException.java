package com.staras.app.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private String errorMessage;

    public EntityNotFoundException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

