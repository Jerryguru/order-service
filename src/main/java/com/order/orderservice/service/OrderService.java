package com.order.orderservice.service;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    // Create Order
    OrderResponse createOrder(OrderRequest request);

    // Get All Orders
    List<OrderResponse> getAllOrders();

    // Get Order By ID
    OrderResponse getOrderById(Long id);

    // Update Order
    OrderResponse updateOrder(Long id, OrderRequest request);

    // Partial Update Order
    OrderResponse partialUpdateOrder(Long id, OrderRequest request);

    // Delete Order
    void deleteOrder(Long id);

    // ========================== CRUD APIs complete ==========================

    // ==========================
   // Get Order By Order Number
  // ==========================

    OrderResponse getOrderByOrderNumber(String orderNumber);


    // ==========================
    // Get Orders By Customer ID
    // ==========================

    List<OrderResponse> getOrdersByCustomerId(Long customerId);


    // ==========================
    // Get Orders By Product ID
    // ==========================

    List<OrderResponse> getOrdersByProductId(Long productId);


    // ==========================
    // Get Orders By Order Status
    // ==========================

    List<OrderResponse> getOrdersByOrderStatus(OrderStatus orderStatus);



}