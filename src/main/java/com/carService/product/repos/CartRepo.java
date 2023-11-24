package com.carService.product.repos;

import com.carService.product.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepo extends JpaRepository<Cart,Long> {

}
