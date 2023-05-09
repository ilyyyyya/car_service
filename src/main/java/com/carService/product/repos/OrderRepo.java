package com.carService.product.repos;

import com.carService.product.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findOrderByName(String name);
}
