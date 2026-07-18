package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;   //JpaRepository--> Spring already ready ga [save(),findById(),findAll(),delete(),existsById()--> ichina interface,Andulo CRUD methods already untayi,Manam malli rayalsina avasaram ledu
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {   //Order-->Order Entity,Long-->Primary Key

    // ==========================
    // Get Order By Order Number
    // ==========================

    Optional<Order> findByOrderNumber(String orderNumber);
}

