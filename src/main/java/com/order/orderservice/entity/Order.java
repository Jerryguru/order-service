package com.order.orderservice.entity;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    // ==========================================================
    // Primary Key
    // Auto Incremented by Database
    // ==========================================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ==========================================================
    // Unique Order Number
    // Example : ORD-1001
    // ==========================================================
    @Column(
            name = "order_number",
            unique = true,
            nullable = false,
            length = 50
    )
    private String orderNumber;


    // ==========================================================
    // Customer ID
    // ==========================================================
    @Column(
            name = "customer_id",
            nullable = false
    )
    private Long customerId;


    // ==========================================================
    // Product ID
    // ==========================================================
    @Column(
            name = "product_id",
            nullable = false
    )
    private Long productId;


    // ==========================================================
    // Quantity Ordered
    // ==========================================================
    @Column(nullable = false)
    private Integer quantity;


    // ==========================================================
    // Price Per Product
    // ==========================================================
    @Column(nullable = false)
    private Double price;


    // ==========================================================
    // Total Order Amount
    // ==========================================================
    @Column(
            name = "total_amount",
            nullable = false
    )
    private Double totalAmount;


    // ==========================================================
    // Order Status
    //
    // Store Enum as String in Database
    //
    // Example:
    // PENDING
    // CONFIRMED
    // SHIPPED
    // DELIVERED
    // ==========================================================
    @Enumerated(EnumType.STRING)
    @Column(
            name = "order_status",
            nullable = false
    )
    private OrderStatus orderStatus;


    // ==========================================================
    // Payment Status
    //
    // Store Enum as String in Database
    //
    // Example:
    // PAID
    // PENDING
    // FAILED
    // REFUNDED
    // ==========================================================
    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_status",
            nullable = false
    )
    private PaymentStatus paymentStatus;


   // ==========================================================
    // Order Date & Time
    // ==========================================================
    @Column(
            name = "order_date",
            nullable = false
    )
    private LocalDateTime orderDate;


    // ==========================================================
    // Record Creation Date & Time
    // ==========================================================
    @Column(name = "created_date")
    private LocalDateTime createdDate;


    // ==========================================================
    // Record Last Updated Date & Time
    // ==========================================================
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}





































































































/*
package com.order.orderservice.entity;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_number",unique = true,nullable = false,length = 50)
    private String orderNumber;

    @Column(name="customer_id",nullable = false)
    private Long customerId;

    @Column(name="product_id",nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(name="total_amount",nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)  // @Enumerated--> tells Hibernate how to store Enum values in the database.
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name ="order_date",nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
*/
