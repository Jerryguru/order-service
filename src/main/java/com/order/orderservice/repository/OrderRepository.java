package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;   //JpaRepository--> Spring already ready ga [save(),findById(),findAll(),delete(),existsById()--> ichina interface,Andulo CRUD methods already untayi,Manam malli rayalsina avasaram ledu
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {   //Order-->Order Entity,Long-->Primary Key

    // ==========================
    // Get Order By Order Number
    // ==========================

    Optional<Order> findByOrderNumber(String orderNumber);

    // ==========================
    // Get Orders By Customer ID
    // ==========================

    List<Order> findByCustomerId(Long customerId);

    // ==========================
    // Get Orders By Product ID
    // ==========================

    List<Order> findByProductId(Long productId);


    // ==========================
    // Get Orders By Order Status
    // ==========================

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}

