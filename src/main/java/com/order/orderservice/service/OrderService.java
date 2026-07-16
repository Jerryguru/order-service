package com.order.orderservice.service;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest request);

    OrderResponse partialUpdateOrder(Long id, OrderRequest request);

    String deleteOrder(Long id);

}