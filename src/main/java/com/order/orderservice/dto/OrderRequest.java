package com.order.orderservice.dto;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    // ==========================================================
    // Unique Order Number
    // Example : ORD-1001
    // ==========================================================

    @NotBlank(message = "Order number is required")
    private String orderNumber;


    // ==========================================================
    // Customer ID
    // Must be greater than zero
    // ==========================================================

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be greater than zero")
    private Long customerId;


    // ==========================================================
    // Product ID
    // Must be greater than zero
    // ==========================================================

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be greater than zero")
    private Long productId;


    // ==========================================================
    // Quantity
    // Minimum value should be 1
    // ==========================================================

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;


    // ==========================================================
    // Product Price
    // Must be greater than zero
    // ==========================================================

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;


    // ==========================================================
    // Total Amount
    // Must be greater than zero
    // ==========================================================

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be greater than zero")
    private Double totalAmount;


    // ==========================================================
    // Order Status
    // Example :
    // PENDING
    // CONFIRMED
    // SHIPPED
    // DELIVERED
    // ==========================================================

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;


    // ==========================================================
    // Payment Status
    // Example :
    // PENDING
    // PAID
    // FAILED
    // REFUNDED
    // ==========================================================

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;


    // ==========================================================
    // Order Date
    // Date and Time when order is placed
    // ==========================================================

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

}








































































/*
package com.order.orderservice.dto;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be greater than zero")
    private Long customerId;

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be greater than zero")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be greater than zero")
    private Double totalAmount;

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;

    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;

}*/
