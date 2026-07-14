package com.order.orderservice.repository;

import com.order.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;   //JpaRepository--> Spring already ready ga ichina interface,Andulo CRUD methods already untayi,Manam malli rayalsina avasaram ledu

public interface OrderRepository extends JpaRepository< Order,Long > {   //Order-->Order Entity,Long-->Primary Key
}
