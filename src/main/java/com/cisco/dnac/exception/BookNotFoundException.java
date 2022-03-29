package com.cisco.dnac.exception;

public class BookNotFoundException extends RuntimeException {
    private String message;

    public BookNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public BookNotFoundException() {
    }

}
