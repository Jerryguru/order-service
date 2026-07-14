package com.order.orderservice.dto;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long id;

    private String orderName;

    private Long customerId;

    private Long productId;

    private Integer quantity;

    private Double price;

    private Double totalAmount;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private LocalDateTime orderDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
