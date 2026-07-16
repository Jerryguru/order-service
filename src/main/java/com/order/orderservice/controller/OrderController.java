package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController            //@RestController is a Spring Boot annotation that marks a class as a REST API controller, enabling it to handle HTTP requests and return data (typically JSON) directly in the response body.

@RequestMapping("/orders")  //@RequestMapping("/orders") is used to define the base URL (base path) for all endpoints inside the controller.

public class OrderController {

    private  final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping                                                                     //@PostMapping is a Spring Boot annotation used to handle HTTP POST requests. It is mainly used to create or save new resources in the application.
    public OrderResponse createOrder(@RequestBody OrderRequest request){
                                      return orderService.createOrder(request);                                              //@RequestBody is a Spring Boot annotation used to bind the HTTP request body to a Java object. It automatically converts incoming JSON data into a Java object.
    }

    @GetMapping
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();

    }
}
