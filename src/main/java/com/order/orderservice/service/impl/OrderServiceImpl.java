package com.order.orderservice.service.impl;

// OrderServiceImpl (Business Logic Layer) We Provide  Business Logics Here

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.entity.Order;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service            //@Service -->tells Spring that this class contains business logic and should be managed as a Spring Bean.
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }


  /*
  * @Override //"This method is implementing (or overriding)
  *  an existing method from a parent class or interface."
  * */

    // -------------------- CreateOrder --------------------

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        Order order = new Order();

        order.setOrderNumber(request.getOrderNumber());

        order.setCustomerId(request.getCustomerId());

        order.setProductId(request.getProductId());

        order.setQuantity(request.getQuantity());

        order.setPrice(request.getPrice());

        order.setTotalAmount(request.getTotalAmount());

        order.setOrderStatus(request.getOrderStatus());

        order.setPaymentStatus(request.getPaymentStatus());

        order.setOrderDate(request.getOrderDate());

        return null;
    }

    // -------------------- getAllOrders --------------------

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    // -------------------- getOrderById --------------------

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    // -------------------- updateOrder --------------------

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        return null;
    }


    // -------------------- partialUpdateOrder --------------------

    @Override
    public OrderResponse partialUpdateOrder(Long id, OrderRequest request) {
        return null;
    }

    // -------------------- deleteOrder --------------------



    @Override
    public String deleteOrder(Long id) {
        return "";
    }
}
