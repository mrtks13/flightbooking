package com.finartz.flightbooking.exception;

public class ValidationException extends RuntimeException {

    private String errorMessage;

    public ValidationException() {
        super();
    }

    public ValidationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }


}
