package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import com.order.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    // ===========================================
// Get Orders By Product ID
// ===========================================

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByProductId(
            @PathVariable Long productId) {

        // Log incoming request
        log.info("Received request to fetch orders for product id : {}", productId);

        // Call service layer
        List<OrderResponse> response = orderService.getOrdersByProductId(productId);

        // Log successful response
        log.info("Successfully fetched {} orders for product id : {}",
                response.size(), productId);

        // Return HTTP 200 OK with response body
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



    // ==========================
// Get Orders By Order Status
// ==========================

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getOrdersByOrderStatus(

            // Read Order Status from URL Path
            @PathVariable OrderStatus orderStatus) {

        // Log incoming request
        log.info("Received request to fetch orders with status : {}", orderStatus);

        // Call Service Layer to fetch orders based on status
        List<OrderResponse> response = orderService.getOrdersByOrderStatus(orderStatus);

        // Log successful response
        log.info("Successfully fetched orders with status : {}", orderStatus);

        // Return HTTP 200 OK along with list of orders
        return ResponseEntity.ok(response);
    }


    // ==========================
// Get Orders By Payment Status
// ==========================

    @GetMapping("/payment-status/{paymentStatus}")
    public ResponseEntity<List<OrderResponse>> getOrdersByPaymentStatus(

            // Read Payment Status from URL Path
            @PathVariable PaymentStatus paymentStatus) {

        // Log incoming request
        log.info("Received request to fetch orders with payment status : {}", paymentStatus);

        // Call Service Layer to fetch orders based on payment status
        List<OrderResponse> response = orderService.getOrdersByPaymentStatus(paymentStatus);

        // Log successful response
        log.info("Successfully fetched orders with payment status : {}", paymentStatus);

        // Return HTTP 200 OK along with list of orders
        return ResponseEntity.ok(response);
    }

    // ==========================
// Get Orders Between Two Dates
// ==========================

    @GetMapping("/date-range")
    public ResponseEntity<List<OrderResponse>> getOrdersBetweenTwoDates(

            // -------------------- Start Date from Request Parameter --------------------

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            // -------------------- End Date from Request Parameter --------------------

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)     //Spring request parameter ni LocalDate ga parse cheyyadaniki ISO date format use chesthundi.
            LocalDate endDate) {

        log.info("Received request to fetch orders between {} and {}", startDate, endDate);

        // -------------------- Step 1 : Call Service Layer --------------------

        List<OrderResponse> response = orderService.getOrdersBetweenDates(startDate, endDate);

        log.info("Successfully fetched orders between {} and {}", startDate, endDate);

        // -------------------- Step 2 : Return Response --------------------

        return ResponseEntity.ok(response);
    }



    // ==========================
    // Get Today's Orders
    // ==========================

    @GetMapping("/today")
    public ResponseEntity<List<OrderResponse>> getTodayOrders() {

        log.info("Received request to fetch today's orders.");

        // -------------------- Step 1 : Call Service Layer --------------------

        List<OrderResponse> response = orderService.getTodayOrders();

        // -------------------- Step 2 : Return Success Response --------------------

        log.info("Successfully fetched {} today's orders.", response.size());

        return ResponseEntity.ok(response);
    }

}
