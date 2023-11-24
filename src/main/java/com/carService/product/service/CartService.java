package com.carService.product.service;

import com.carService.product.entity.AutoPart;
import com.carService.product.entity.Cart;
import com.carService.product.entity.CartItem;
import com.carService.product.repos.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

}
