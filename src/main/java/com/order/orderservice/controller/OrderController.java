
package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import com.order.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.order.orderservice.dto.PageResponse;

import java.time.LocalDate;
import java.util.List;

// ==========================================================
// Order Controller
//
// Handles all REST API requests related to Orders.
// ==========================================================
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    // ==========================================================
    // Service Dependency
    // ==========================================================
    private final OrderService orderService;

    // ==========================================================
    // Constructor Injection
    // ==========================================================
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ==========================================================
    // Create Order
    // ==========================================================
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest request) {

        log.info("Received request to create order with Order Number : {}",
                request.getOrderNumber());

        // Call Service Layer
        OrderResponse response = orderService.createOrder(request);

        log.info("Order created successfully with ID : {}",
                response.getId());

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get All Orders
    // Pagination + Sorting
    // ==========================================================
    @GetMapping
    public ResponseEntity<PageResponse<OrderResponse>> getAllOrders(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // Log Incoming Request
        log.info(
                "Received request to fetch orders | Page : {} | Size : {} | Sort By : {} | Direction : {}",
                page,
                size,
                sortBy,
                direction);

        // Call Service Layer
        PageResponse<OrderResponse> response =
                orderService.getAllOrders(
                        page,
                        size,
                        sortBy,
                        direction);

        // Success Log
        log.info(
                "Successfully fetched page {} with {} records",
                page,
                response.getContent().size());

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get Order By ID
    // ==========================================================
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id) {

        log.info("Received request to fetch order with ID : {}", id);

        // Call Service Layer
        OrderResponse response =
                orderService.getOrderById(id);

        log.info("Successfully fetched order with ID : {}", id);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Update Order
    // ==========================================================
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(

            @PathVariable Long id,

            @RequestBody OrderRequest request) {

        log.info("Received request to update order with ID : {}", id);

        // Call Service Layer
        OrderResponse response =
                orderService.updateOrder(id, request);

        log.info("Successfully updated order with ID : {}", id);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Partial Update Order
    // ==========================================================
    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> partialUpdateOrder(

            @PathVariable Long id,

            @RequestBody OrderRequest request) {

        log.info("Received request to partially update order with ID : {}", id);

        // Call Service Layer
        OrderResponse response =
                orderService.partialUpdateOrder(id, request);

        log.info("Successfully partially updated order with ID : {}", id);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Delete Order
    // ==========================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long id) {

        log.info("Received request to delete order with ID : {}", id);

        // Call Service Layer
        orderService.deleteOrder(id);

        log.info("Successfully deleted order with ID : {}", id);

        // Return Success Response
        return ResponseEntity.ok("Order deleted successfully.");
    }

    // ==========================================================
    // Get Order By Order Number
    // ==========================================================
    @GetMapping("/order-number/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderByOrderNumber(
            @PathVariable String orderNumber) {

        // Log Incoming Request
        log.info("Received request to fetch order with Order Number : {}",
                orderNumber);

        // Call Service Layer
        OrderResponse response =
                orderService.getOrderByOrderNumber(orderNumber);

        // Success Log
        log.info("Successfully fetched order with Order Number : {}",
                orderNumber);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get Orders By Product ID
    // Pagination + Sorting
    // ==========================================================
    @GetMapping("/product/{productId}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByProductId(

            @PathVariable Long productId,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // Log Incoming Request
        log.info(
                "Received request to fetch orders for Product ID : {}",
                productId);

        // Call Service Layer
        PageResponse<OrderResponse> response =
                orderService.getOrdersByProductId(
                        productId,
                        page,
                        size,
                        sortBy,
                        direction);

        // Success Log
        log.info(
                "Successfully fetched orders for Product ID : {}",
                productId);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get Orders By Customer ID
    // Pagination + Sorting
    // ==========================================================
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByCustomerId(

            @PathVariable Long customerId,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // Log Incoming Request
        log.info(
                "Received request to fetch orders for Customer ID : {}",
                customerId);

        // Call Service Layer
        PageResponse<OrderResponse> response =
                orderService.getOrdersByCustomerId(
                        customerId,
                        page,
                        size,
                        sortBy,
                        direction);

        // Success Log
        log.info(
                "Successfully fetched orders for Customer ID : {}",
                customerId);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get Orders By Order Status
    // Pagination + Sorting
    // ==========================================================
    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByOrderStatus(

            @PathVariable OrderStatus orderStatus,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // Log Incoming Request
        log.info(
                "Received request to fetch orders with Status : {}",
                orderStatus);

        // Call Service Layer
        PageResponse<OrderResponse> response =
                orderService.getOrdersByOrderStatus(
                        orderStatus,
                        page,
                        size,
                        sortBy,
                        direction);

        // Success Log
        log.info(
                "Successfully fetched orders with Status : {}",
                orderStatus);

        // Return Response
        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // Get Orders By Payment Status
    // Pagination + Sorting
    // ==========================================================
    @GetMapping("/payment-status/{paymentStatus}/pagination-sorting")
    public PageResponse<OrderResponse> getOrdersByPaymentStatus(

            @PathVariable PaymentStatus paymentStatus,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // Log Incoming Request
        log.info(
                "Fetching Orders | Payment Status : {} | Page : {} | Size : {} | Sort By : {} | Direction : {}",
                paymentStatus,
                page,
                size,
                sortBy,
                direction);

        // Call Service Layer
        PageResponse<OrderResponse> response =
                orderService.getOrdersByPaymentStatus(
                        paymentStatus,
                        page,
                        size,
                        sortBy,
                        direction);

        // Success Log
        log.info(
                "Successfully fetched orders with Payment Status : {}",
                paymentStatus);

        // Return Response
        return response;
    }

    // ==========================================================
    // Get Orders Between Date Range
    // Pagination + Sorting
    // ==========================================================
    @GetMapping("/date-range/pagination-sorting")
    public PageResponse<OrderResponse> getOrdersBetweenDates(

            // ==========================================================
            // Start Date
            // Example : 2026-07-01
            // ==========================================================
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            // ==========================================================
            // End Date
            // Example : 2026-07-31
            // ==========================================================
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            // ==========================================================
            // Pagination Parameters
            // ==========================================================
            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            // ==========================================================
            // Sorting Parameters
            // ==========================================================
            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "ASC") String direction) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info(
                "Fetching orders between {} and {} | Page : {} | Size : {} | Sort By : {} | Direction : {}",
                startDate,
                endDate,
                page,
                size,
                sortBy,
                direction);

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        PageResponse<OrderResponse> response =
                orderService.getOrdersBetweenDates(
                        startDate,
                        endDate,
                        page,
                        size,
                        sortBy,
                        direction);

        // ==========================================================
        // Success Log
        // ==========================================================
        log.info(
                "Successfully fetched orders between {} and {}",
                startDate,
                endDate);

        // ==========================================================
        // Return Response
        // ==========================================================
        return response;
    }

    // ==========================================================
    // Get Today's Orders
    // ==========================================================
    @GetMapping("/today")
    public ResponseEntity<List<OrderResponse>> getTodayOrders() {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info("Received request to fetch today's orders.");

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        List<OrderResponse> response =
                orderService.getTodayOrders();

        // ==========================================================
        // Success Log
        // ==========================================================
        log.info(
                "Successfully fetched {} today's orders.",
                response.size());

        // ==========================================================
        // Return Response
        // ==========================================================
        return ResponseEntity.ok(response);
    }

}

































































































































































































































































/*
package com.order.orderservice.controller;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.dto.PageResponse;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import com.order.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
// ==========================
// Get All Orders With Pagination And Sorting
// ==========================

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAllOrders(

            // Current page number
            // Default value = 0 (First Page)
            @RequestParam(defaultValue = "0") int page,

            // Number of records per page
            // Default value = 10
            @RequestParam(defaultValue = "10") int size,

            // Sort Field
            // Default value = id
            @RequestParam(defaultValue = "id") String sortBy,

            // Sort Direction
            // Default value = ASC
            @RequestParam(defaultValue = "ASC") String direction) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info("Received request to fetch orders | Page : {} | Size : {} | Sort By : {} | Direction : {}",
                page, size, sortBy, direction);

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        Page<OrderResponse> response =
                orderService.getAllOrders(page, size, sortBy, direction);

        // ==========================================================
        // Log Success
        // ==========================================================
        log.info("Successfully fetched page {} with {} records",
                page,
                response.getNumberOfElements());

        // ==========================================================
        // Return Response
        // ==========================================================
        return ResponseEntity.ok(response);
    }

 */
/*   // -------------------- Get All Orders --------------------

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        log.info("Received request to fetch all orders");

        // Call Service Layer

        List<OrderResponse> responses = orderService.getAllOrders();

        log.info("Returning {} orders", responses.size());

        // Return Response

        return ResponseEntity.ok(responses);

    }*//*


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




    // ==========================================================
// Get Product Orders With Pagination + Sorting
// ==========================================================
    @GetMapping("/product/{productId}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByProductId(

            // ==========================================================
            // Product ID From URL
            // ==========================================================
            @PathVariable Long productId,

            // ==========================================================
            // Current Page Number
            // Default = 0
            // ==========================================================
            @RequestParam(defaultValue = "0") int page,

            // ==========================================================
            // Records Per Page
            // Default = 5
            // ==========================================================
            @RequestParam(defaultValue = "5") int size,

            // ==========================================================
            // Sort Field
            // Default = id
            // ==========================================================
            @RequestParam(defaultValue = "id") String sortBy,

            // ==========================================================
            // Sort Direction
            // Default = ASC
            // ==========================================================
            @RequestParam(defaultValue = "ASC") String direction) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info("Received request to fetch orders for product ID : {}", productId);

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        PageResponse<OrderResponse> response =
                orderService.getOrdersByProductId(
                        productId,
                        page,
                        size,
                        sortBy,
                        direction);

        // ==========================================================
        // Log Success
        // ==========================================================
        log.info("Successfully fetched product orders for product ID : {}", productId);

        // ==========================================================
        // Return Response
        // ==========================================================
        return ResponseEntity.ok(response);
    }

   */
/* // ===========================================
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
*//*

    // ==========================================================
// Get Customer Orders With Pagination + Sorting
// ==========================================================
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<PageResponse<OrderResponse>> getOrdersByCustomerId(

            // ==========================================================
            // Customer ID From URL
            // ==========================================================
            @PathVariable Long customerId,

            // ==========================================================
            // Current Page Number
            // Default = 0
            // ==========================================================
            @RequestParam(defaultValue = "0") int page,

            // ==========================================================
            // Number Of Records Per Page
            // Default = 5
            // ==========================================================
            @RequestParam(defaultValue = "5") int size,

            // ==========================================================
            // Sort Field
            // Default = id
            // ==========================================================
            @RequestParam(defaultValue = "id") String sortBy,

            // ==========================================================
            // Sort Direction
            // Default = ASC
            // ==========================================================
            @RequestParam(defaultValue = "ASC") String direction) {

        log.info("Received request to fetch customer orders. Customer ID : {}", customerId);

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        PageResponse<OrderResponse> response =
                orderService.getOrdersByCustomerId(
                        customerId,
                        page,
                        size,
                        sortBy,
                        direction);

        log.info("Successfully fetched customer orders. Customer ID : {}", customerId);

        // ==========================================================
        // Return Response
        // ==========================================================
        return ResponseEntity.ok(response);
    }

   */
/* // ==========================
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
*//*



   */
/* // ==========================
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
*//*

   // ==========================================================
// Get Orders By Order Status With Pagination + Sorting
// ==========================================================
   @GetMapping("/status/{orderStatus}")
   public ResponseEntity<PageResponse<OrderResponse>> getOrdersByOrderStatus(

           // ==========================================================
           // Read Order Status From URL
           // Example : /orders/status/DELIVERED
           // ==========================================================
           @PathVariable OrderStatus orderStatus,

           // ==========================================================
           // Pagination Parameters
           // ==========================================================
           @RequestParam(defaultValue = "0") int page,

           @RequestParam(defaultValue = "5") int size,

           // ==========================================================
           // Sorting Parameters
           // ==========================================================
           @RequestParam(defaultValue = "id") String sortBy,

           @RequestParam(defaultValue = "ASC") String direction) {

       // ==========================================================
       // Log Incoming Request
       // ==========================================================
       log.info("Received request to fetch orders with status : {}", orderStatus);

       // ==========================================================
       // Call Service Layer
       // ==========================================================
       PageResponse<OrderResponse> response =
               orderService.getOrdersByOrderStatus(
                       orderStatus,
                       page,
                       size,
                       sortBy,
                       direction);

       // ==========================================================
       // Log Success
       // ==========================================================
       log.info("Successfully fetched orders with status : {}", orderStatus);

       // ==========================================================
       // Return Response
       // ==========================================================
       return ResponseEntity.ok(response);
   }


    // ==========================================================
// Get Orders By Payment Status
// Pagination + Sorting
// ==========================================================
    @GetMapping("/payment-status/{paymentStatus}/pagination-sorting")
    public PageResponse<OrderResponse> getOrdersByPaymentStatus(

            @PathVariable PaymentStatus paymentStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction

    ) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info("Fetching Orders | PaymentStatus={} | Page={} | Size={} | SortBy={} | Direction={}",
                paymentStatus,
                page,
                size,
                sortBy,
                direction
        );

        // ==========================================================
        // Call Service Layer
        // ==========================================================
        PageResponse<OrderResponse> response =
                orderService.getOrdersByPaymentStatus(
                        paymentStatus,
                        page,
                        size,
                        sortBy,
                        direction
                );

        // ==========================================================
        // Success Log
        // ==========================================================
        log.info("Orders Retrieved Successfully");

        return response;
    }


*/
/*
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
*//*


   */
/* // ==========================
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

*//*



    // ==========================================================
// Get Orders Between Date Range
// Pagination + Sorting
// ==========================================================
    @GetMapping("/date-range/pagination-sorting")
    public PageResponse<OrderResponse> getOrdersBetweenDates(

            // Start Date
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            // End Date
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            // Page Number (Default = 0)
            @RequestParam(defaultValue = "0")
            int page,

            // Records Per Page (Default = 5)
            @RequestParam(defaultValue = "5")
            int size,

            // Sort Field (Default = id)
            @RequestParam(defaultValue = "id")
            String sortBy,

            // Sort Direction (Default = ASC)
            @RequestParam(defaultValue = "ASC")
            String direction

    ) {

        // Call Service Layer
        return orderService.getOrdersBetweenDates(
                startDate,
                endDate,
                page,
                size,
                sortBy,
                direction
        );

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
*/
