package com.krimo.daevitserver.exception;

import com.krimo.daevitserver.dto.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ResponseBody> handleApiException(ApiException e) {
        return new ResponseEntity<>(
            new ResponseBody(
                e.getHttpStatus().value(), 
                null, 
                e.getMessage()
                ), 
            e.getHttpStatus()
        );
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ResponseBody> handleNSEException(NoSuchElementException e) {
        return new ResponseEntity<>(
                new ResponseBody(404, null, e.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
