package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ==========================================================
    // Get Order By Order Number
    // ==========================================================
    Optional<Order> findByOrderNumber(String orderNumber);

    // ==========================================================
    // Get Orders By Customer ID
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByCustomerId(
            Long customerId,
            Pageable pageable);

    // ==========================================================
    // Get Orders By Product ID
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByProductId(
            Long productId,
            Pageable pageable);

    // ==========================================================
    // Get Orders By Order Status
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByOrderStatus(
            OrderStatus orderStatus,
            Pageable pageable);

    // ==========================================================
    // Get Orders By Payment Status
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByPaymentStatus(
            PaymentStatus paymentStatus,
            Pageable pageable);

    // ==========================================================
    // Get Orders Between Date Range
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByOrderDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);

    // ==========================================================
    // Get Today's Orders
    // ==========================================================
    List<Order> findByOrderDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate);

}

























/*
package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // ==========================================================
    // NOTE
    // ==========================================================
    // JpaRepository provides the following methods automatically:
    //
    // save()
    // findById()
    // findAll()
    // delete()
    // existsById()
    //
    // So, we don't need to write CRUD methods manually.
    // ==========================================================


    // ==========================================================
    // Get Order By Order Number
    // ==========================================================
    Optional<Order> findByOrderNumber(String orderNumber);


    // ==========================================================
    // Get Orders By Customer ID
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByCustomerId(
            Long customerId,
            Pageable pageable
    );


    // ==========================================================
    // Get Orders By Product ID
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByProductId(
            Long productId,
            Pageable pageable
    );


    // ==========================================================
    // Get Orders By Order Status
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByOrderStatus(
            OrderStatus orderStatus,
            Pageable pageable
    );


    // ==========================================================
    // Get Orders By Payment Status
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByPaymentStatus(
            PaymentStatus paymentStatus,
            Pageable pageable
    );


    // ==========================================================
    // Get Orders Between Date Range
    // Pagination + Sorting
    // ==========================================================
    Page<Order> findByOrderDateBetween(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );*/


    /*
    // ==========================================================
    // Old Methods (Without Pagination)
    // ==========================================================

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByProductId(Long productId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);

    List<Order> findByOrderDateBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    );

    List<Order> findByOrderDateBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    );
    */



















































































































/*
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

  */
/*  // ==========================
    // Get Orders By Customer ID
    // ==========================

    List<Order> findByCustomerId(Long customerId);*//*



    // ==========================================================
// Fetch Product Orders With Pagination + Sorting
// ==========================================================
    Page<Order> findByProductId(Long productId, Pageable pageable);

   */
/* // ==========================
    // Get Orders By Product ID
    // ==========================

    List<Order> findByProductId(Long productId);*//*



    // ==========================================================
    // Fetch Orders By Order Status With Pagination + Sorting
    // ==========================================================
    Page<Order> findByOrderStatus(
            OrderStatus orderStatus,
            Pageable pageable
    );

   */
/* // ==========================
    // Get Orders By Order Status
    // ==========================

    List<Order> findByOrderStatus(OrderStatus orderStatus);*//*



    // ==========================================================
// Pagination + Sorting + Payment Status
// ==========================================================
    Page<Order> findByPaymentStatus(
            PaymentStatus paymentStatus,
            Pageable pageable
    );


  */
/*  // ==========================
    // Get Orders By Payment Status
    // ==========================

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);*//*



    // ==========================================================
// Date Range + Pagination + Sorting
// ==========================================================
    Page<Order> findByOrderDateBetween(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
*/
/*

// ==========================
// Get Orders Between Two Dates
// ==========================

    List<Order> findByOrderDateBetween(LocalDateTime startDate,
                                       LocalDateTime endDate);

*//*



 */
/*   // ==========================
    // Get Today's Orders
    // ==========================

    List<Order> findByOrderDateBetween(LocalDateTime startDateTime,
                                       LocalDateTime endDateTime);*//*

}

*/
