package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;   //JpaRepository--> Spring already ready ga [save(),findById(),findAll(),delete(),existsById()--> ichina interface,Andulo CRUD methods already untayi,Manam malli rayalsina avasaram ledu
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {   //Order-->Order Entity,Long-->Primary Key

    // ==========================================================
    // NOTE:
    // JpaRepository already provides CRUD Operations
    // and Pagination support.
    //

    // ==========================
    // Get Order By Order Number
    // ==========================

    Optional<Order> findByOrderNumber(String orderNumber);

    // ==========================================================
// Fetch Customer Orders With Pagination + Sorting
// ==========================================================
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

  /*  // ==========================
    // Get Orders By Customer ID
    // ==========================

    List<Order> findByCustomerId(Long customerId);*/


    // ==========================================================
// Fetch Product Orders With Pagination + Sorting
// ==========================================================
    Page<Order> findByProductId(Long productId, Pageable pageable);

   /* // ==========================
    // Get Orders By Product ID
    // ==========================

    List<Order> findByProductId(Long productId);*/


    // ==========================
    // Get Orders By Order Status
    // ==========================

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    // ==========================
    // Get Orders By Payment Status
    // ==========================

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);

// ==========================
// Get Orders Between Two Dates
// ==========================

    List<Order> findByOrderDateBetween(LocalDateTime startDate,
                                       LocalDateTime endDate);



 /*   // ==========================
    // Get Today's Orders
    // ==========================

    List<Order> findByOrderDateBetween(LocalDateTime startDateTime,
                                       LocalDateTime endDateTime);*/
}

