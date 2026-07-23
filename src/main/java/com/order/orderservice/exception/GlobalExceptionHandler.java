package com.order.orderservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

// ==========================================================
// Global Exception Handler
//
// Handles exceptions thrown by all REST Controllers
// and returns a standard JSON error response.
// ==========================================================
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==========================================================
    // Handle OrderNotFoundException
    // ==========================================================
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(

            OrderNotFoundException exception,
            HttpServletRequest request) {

        // ==========================================================
        // Build Error Response
        // ==========================================================
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        // ==========================================================
        // Return HTTP 404 Response
        // ==========================================================
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

}




















































































































/*
package com.order.orderservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice             //@RestControllerAdvice is a Spring Boot annotation that catches exceptions from all REST controllers and returns a consistent JSON response to the client.
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)     //@ExceptionHandler is a Spring Boot annotation used to catch and handle specific exceptions, returning a custom error response instead of the default error.

    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(
            OrderNotFoundException exception, HttpServletRequest request){

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build();

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);





    }

}
*/
