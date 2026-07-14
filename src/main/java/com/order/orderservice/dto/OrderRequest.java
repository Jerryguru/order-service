package com.order.orderservice.dto;

import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {

    @NotBlank(message = "Order number is Required")  //@NotBlank String Should not be Empty
    private String orderNumber;

    @NotNull(message = "Customer Id is required")   //@NotNull value is Compalsory
    @Positive(message = "Customer Id must be greater than zero")  //@Positive Value always >GreaterThan 0
    private Long customerId;

    @NotNull(message = "Product Id is required")
    @Positive(message = "Product Id must be greater than zero")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1,message = "Quantity should be at least 1")  //@Min Value is 1
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double totalAmount;

    @NotNull(message = "Order Status is required")
    private OrderStatus orderStatus;

    @NotNull(message = "Payment Status is required")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Order Date is required")
    private LocalDateTime orderDate;
}
