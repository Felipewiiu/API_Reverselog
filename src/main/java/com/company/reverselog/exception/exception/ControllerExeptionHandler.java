package com.company.reverselog.exception.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeptionHandler {
    private StandardError error = new StandardError();

    public ResponseEntity<StandardError> EntityNotFold(ControllerNotFoundExeption e, HttpServletRequest request){
        HttpStatus status = HttpStatus.MULTI_STATUS;

        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Entity Not Fold");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.error);
    }
}

