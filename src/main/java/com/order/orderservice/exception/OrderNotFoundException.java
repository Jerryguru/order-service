package com.order.orderservice.exception;

// ==========================================================
// Custom Exception
//
// This exception is thrown whenever
// the requested Order is not found.
// ==========================================================
public class OrderNotFoundException extends RuntimeException {

    // ==========================================================
    // Constructor
    //
    // Pass custom error message to parent RuntimeException class
    // ==========================================================
    public OrderNotFoundException(String message) {
        super(message);
    }

}















/*
package com.order.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }
}
*/
