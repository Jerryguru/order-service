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
