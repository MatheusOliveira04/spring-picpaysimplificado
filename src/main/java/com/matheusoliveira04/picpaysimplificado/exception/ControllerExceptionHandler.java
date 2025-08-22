package com.matheusoliveira04.picpaysimplificado.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> getObjectNotFoundException(
            ObjectNotFoundException e,
            HttpServletRequest request) {
        exceptionMessageLog(e);
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
        exceptionMessageLog(e);
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
        exceptionMessageLog(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI(),
                        List.of(e.getMessage())
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> getMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        exceptionMessageLog(e);
        List<String> allErrors = e.getAllErrors()
                .stream()
                .map(error -> extractMessage( ((FieldError) error).getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI(),
                        allErrors
                ));
    }

    private void exceptionMessageLog(Exception e) {
        logger.error(e.getClass().getSimpleName() + " " + e.getMessage());
    }

    private String extractMessage(String field, String message) {
        return new StringBuilder()
                .append(field)
                .append(": ")
                .append(message)
                .toString();
    }

}
