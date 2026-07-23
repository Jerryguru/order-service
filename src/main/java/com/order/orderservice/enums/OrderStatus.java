
package com.order.orderservice.enums;

// ==========================================================
// Order Status Enum
//
// Represents the current processing stage of an order.
// ==========================================================
public enum OrderStatus {

    // ==========================================================
    // Order has been placed successfully,
    // but processing has not started yet.
    // ==========================================================
    PENDING,

    // ==========================================================
    // Order has been confirmed by the seller.
    // ==========================================================
    CONFIRMED,

    // ==========================================================
    // Order is currently being processed.
    // ==========================================================
    PROCESSING,

    // ==========================================================
    // Order has been shipped to the customer.
    // ==========================================================
    SHIPPED,

    // ==========================================================
    // Order has been successfully delivered.
    // ==========================================================
    DELIVERED,

    // ==========================================================
    // Order has been cancelled.
    // ==========================================================
    CANCELLED,

    // ==========================================================
    // Customer returned the order after delivery.
    // ==========================================================
    RETURNED

}


























































/*
package com.order.orderservice.enums;

public enum OrderStatus {
    PENDING,

    CONFIRMED,

    PROCESSING,

    SHIPPED,

    DELIVERED,

    CANCELLED,

    RETURNED
}
*/
