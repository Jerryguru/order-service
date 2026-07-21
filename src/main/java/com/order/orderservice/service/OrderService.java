package com.order.orderservice.service;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import com.order.orderservice.dto.PageResponse;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface OrderService {

    // Create Order
    OrderResponse createOrder(OrderRequest request);

  // // Get All Orders
//    List<OrderResponse> getAllOrders();

    // ==========================================================
// Get All Orders With Pagination
// ==========================================================

    // page -> Which page should be fetched
// size -> Number of records per page
   // Page<OrderResponse> getAllOrders(int page, int size);
    // ==========================================================
// Get All Orders With Pagination And Sorting
// ==========================================================
//
// page      -> Current page number
// size      -> Number of records per page
// sortBy    -> Entity field used for sorting
// direction -> ASC or DESC
//
// ==========================================================

    Page<OrderResponse> getAllOrders(int page,
                                     int size,
                                     String sortBy,
                                     String direction);

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


 /*   // ==========================
    // Get Orders By Customer ID
    // ==========================

    List<OrderResponse> getOrdersByCustomerId(Long customerId);*/


    // ==========================================================
// Get Customer Orders With Pagination + Sorting
// ==========================================================
    PageResponse<OrderResponse> getOrdersByCustomerId(
            Long customerId,
            int page,
            int size,
            String sortBy,
            String direction
    );

    // ==========================================================
// Get Product Orders With Pagination + Sorting
// ==========================================================
    PageResponse<OrderResponse> getOrdersByProductId(
            Long productId,
            int page,
            int size,
            String sortBy,
            String direction
    );


   /* // ==========================
    // Get Orders By Product ID
    // ==========================

    List<OrderResponse> getOrdersByProductId(Long productId);*/


    // ==========================
    // Get Orders By Order Status
    // ==========================

    List<OrderResponse> getOrdersByOrderStatus(OrderStatus orderStatus);

    // ==========================
// Get Orders By Payment Status
// ==========================

    List<OrderResponse> getOrdersByPaymentStatus(PaymentStatus paymentStatus);



    // ==========================
    // Get Orders Between Two Dates
    // ==========================

    List<OrderResponse> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate);


    // ==========================
    // Get Today's Orders
    // ==========================

    List<OrderResponse> getTodayOrders();


}