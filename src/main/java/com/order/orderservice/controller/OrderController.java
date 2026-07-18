package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j

@RestController            //@RestController is a Spring Boot annotation that marks a class as a REST API controller, enabling it to handle HTTP requests and return data (typically JSON) directly in the response body.

@RequestMapping("/orders")  //@RequestMapping("/orders") is used to define the base URL (base path) for all endpoints inside the controller.

public class OrderController {

    private  final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // -------------------- Create Order --------------------

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {

        log.info("Received request to create order with Order Number : {}", request.getOrderNumber());

        // Call Service Layer

        OrderResponse response = orderService.createOrder(request);

        log.info("Order created successfully with ID : {}", response.getId());

        // Return Response

        return ResponseEntity.ok(response);

    }

    // -------------------- Get All Orders --------------------

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        log.info("Received request to fetch all orders");

        // Call Service Layer

        List<OrderResponse> responses = orderService.getAllOrders();

        log.info("Returning {} orders", responses.size());

        // Return Response

        return ResponseEntity.ok(responses);

    }

    // -------------------- Get Order By ID --------------------

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {

        log.info("Received request to fetch order with ID : {}", id);

        // Call Service Layer

        OrderResponse response = orderService.getOrderById(id);

        log.info("Successfully fetched order with ID : {}", id);

        // Return Response

        return ResponseEntity.ok(response);

    }


// -------------------- Update Order --------------------

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,
                                                     @RequestBody OrderRequest request) {

        log.info("Received request to update order with ID : {}", id);

        // Call Service Layer

        OrderResponse response = orderService.updateOrder(id, request);

        log.info("Successfully updated order with ID : {}", id);

        // Return Response

        return ResponseEntity.ok(response);

    }

// -------------------- Partial Update Order --------------------

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> partialUpdateOrder(@PathVariable Long id,
                                                            @RequestBody OrderRequest request) {

        log.info("Received request to partially update order with ID : {}", id);

        OrderResponse response = orderService.partialUpdateOrder(id, request);

        log.info("Successfully partially updated order with ID : {}", id);

        return ResponseEntity.ok(response);
    }

    // -------------------- Delete Order --------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){

        log.info("Recevied request to delete order with ID : {}",id);

        orderService.deleteOrder(id);

        log.info("Sucessfulley deleted order with Id :",id);

        return ResponseEntity.ok("Order deleted sucessfully.");
    }


// ==========================
// Get Order By Order Number
// ==========================

    @GetMapping("/order-number/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderByOrderNumber(@PathVariable String orderNumber) {

        log.info("Received request to fetch order with order number : {}", orderNumber);

        // -------------------- Step 1 : Call Service Layer --------------------

        OrderResponse response = orderService.getOrderByOrderNumber(orderNumber);

        log.info("Successfully fetched order with order number : {}", orderNumber);

        // -------------------- Step 2 : Return Response to Client --------------------

        return ResponseEntity.ok(response);

    }



    // ==========================
// Get Orders By Customer ID
// ==========================

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId) {

        log.info("Received request to fetch orders with customer id : {}", customerId);

        // -------------------- Step 1 : Call Service Layer --------------------

        List<OrderResponse> response = orderService.getOrdersByCustomerId(customerId);

        log.info("Successfully fetched {} orders with customer id : {}", response.size(), customerId);

        // -------------------- Step 2 : Return Response to Client --------------------

        return ResponseEntity.ok(response);

    }


}
