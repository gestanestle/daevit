package com.krimo.daevitserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Map<String, String>> handleApiException(ApiException e) {
        return new ResponseEntity<>(
                Map.of("message", e.getMessage()), e.getHttpStatus()
        );
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNSEException(NoSuchElementException e) {
        return new ResponseEntity<>(
                Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
