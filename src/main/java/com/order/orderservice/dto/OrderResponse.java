package com.order.orderservice.dto;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
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
public class OrderResponse {

    private Long id;

    private String orderNumber;

    private Long customerId;

    private Long productId;

    private Integer quantity;

    private Double price;

    private Double totalAmount;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private LocalDateTime orderDate;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}