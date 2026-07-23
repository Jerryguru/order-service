package com.order.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// ==========================================================
// Error Response DTO
//
// This DTO is returned to the client whenever
// an exception occurs in the application.
// ==========================================================
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // ==========================================================
    // Date & Time when the error occurred
    // ==========================================================
    private LocalDateTime timestamp;


    // ==========================================================
    // HTTP Status Code
    // Example:
    // 404
    // 400
    // 500
    // ==========================================================
    private int status;


    // ==========================================================
    // HTTP Error Name
    // Example:
    // Not Found
    // Bad Request
    // Internal Server Error
    // ==========================================================
    private String error;


    // ==========================================================
    // Detailed Error Message
    // ==========================================================
    private String message;


    // ==========================================================
    // API Path where the exception occurred
    // Example:
    // /orders/10
    // ==========================================================
    private String path;

}










































/*
package com.order.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data                // it creats Getter,Setter,ToString(),equals(),hashCode()
@Builder
@NoArgsConstructor   //Creates empty constructor.
@AllArgsConstructor  //Creates constructor with all fields.
public class ErrorResponse {

    private LocalDateTime timestamp;   //It gives When error happened.

    private int status;

    private String error;

    private String message;

    private String path;
}
*/
