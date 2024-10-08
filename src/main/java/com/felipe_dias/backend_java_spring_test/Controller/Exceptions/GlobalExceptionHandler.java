package com.felipe_dias.backend_java_spring_test.Controller.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {



        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<StandardError> resourceNotFoundHandler(ResourceNotFoundException e, HttpServletRequest request){
            String error = "Resource not found";
            HttpStatus status = HttpStatus.NOT_FOUND;
            StandardError standardError = new StandardError(Instant.now(),
                    status.value(),
                    error, e.getMessage(),
                    request.getServletPath());

            return ResponseEntity.status(status).body(standardError);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<StandardError> illegalArgumentHandler(IllegalArgumentException e, HttpServletRequest request){
            String error = "Illegal argument";
            HttpStatus status = HttpStatus.BAD_REQUEST;
            StandardError standardError = new StandardError(Instant.now(),
                    status.value(),
                    error, e.getMessage(),
                    request.getServletPath());

            return ResponseEntity.status(status).body(standardError);
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<StandardError> globalExceptions(RuntimeException e, HttpServletRequest request){
            String error = e.getMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();

            StandardError standardError = new StandardError(Instant.now(),
                    status.value(),
                    error, error,
                    request.getServletPath());

            return ResponseEntity.status(status).body(standardError);

        }
    }

