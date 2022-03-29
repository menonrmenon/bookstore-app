package com.cisco.dnac.exception;

public class BookAlreadyExistsException extends RuntimeException {
    private String message;

    public BookAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public BookAlreadyExistsException() {
    }

}
