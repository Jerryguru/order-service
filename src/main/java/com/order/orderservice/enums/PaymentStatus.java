package com.order.orderservice.enums;

// ==========================================================
// Payment Status Enum
//
// Represents the current payment status of an order.
// ==========================================================
public enum PaymentStatus {

    // ==========================================================
    // Payment has not been completed yet.
    // ==========================================================
    PENDING,

    // ==========================================================
    // Payment completed successfully.
    // ==========================================================
    PAID,

    // ==========================================================
    // Payment transaction failed.
    // ==========================================================
    FAILED,

    // ==========================================================
    // Payment has been refunded to the customer.
    // ==========================================================
    REFUNDED,

    // ==========================================================
    // Payment was cancelled.
    // ==========================================================
    CANCELLED

}







































/*
package com.order.orderservice.enums;

public enum PaymentStatus {
    PENDING,

    PAID,

    FAILED,

    REFUNDED,

    CANCELLED
}
*/
