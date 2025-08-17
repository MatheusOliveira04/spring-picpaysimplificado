package com.matheusoliveira04.picpaysimplificado.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> getObjectNotFoundException(
            ObjectNotFoundException e,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new StandardError(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI(),
                        List.of(e.getMessage())
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> getDataIntegrityViolationException(
            DataIntegrityViolationException e,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new StandardError(
                        LocalDateTime.now(),
                        HttpStatus.CONFLICT.value(),
                        request.getRequestURI(),
                        List.of(e.getMostSpecificCause().getMessage())
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> getIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI(),
                        List.of(e.getMessage())
                ));
    }

}
