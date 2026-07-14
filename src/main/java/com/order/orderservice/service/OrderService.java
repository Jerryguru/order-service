package com.order.orderservice.service;

import com.order.orderservice.dto.OrderRequest; //Incoming request.
import com.order.orderservice.dto.OrderResponse; //Outgoing response.

import java.util.List;  // Multiple Orders Return

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse>getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id,OrderRequest request);

    OrderResponse partialUpdateOrder(Long id , OrderRequest request);

    String deleteOrder(Long id);
}
