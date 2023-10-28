package com.ecommerce.walmart.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {AppException.class})
    public ResponseEntity<?> handleException(AppException ex) {
        ExceptionBody exception = new ExceptionBody(ex.getMessage(), ex.getStatus().value());
        return new ResponseEntity<>(exception, ex.getStatus());
    }
}
