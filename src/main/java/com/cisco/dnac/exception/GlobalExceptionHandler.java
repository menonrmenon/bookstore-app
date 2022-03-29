package com.cisco.dnac.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity bookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> bookAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>("Book Already Exists", HttpStatus.CONFLICT);
    }
}
