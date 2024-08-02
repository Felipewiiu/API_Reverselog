package com.company.reverselog.exception.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExeptionHandler {
    private StandardError error = new StandardError();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        ValidationError error = new ValidationError();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Validation Error");
        error.setMessage("Validation failed for one or more fields.");
        error.setPath(request.getRequestURI());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((erro) -> {
            String fieldName = ((FieldError) erro).getField();
            String errorMessage = erro.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        error.setFieldErrors(fieldErrors);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ControllerNotFoundExeption.class)
    public ResponseEntity<StandardError> EntityNotFold(ControllerNotFoundExeption e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Entity Not Fold");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.error);
    }
}

