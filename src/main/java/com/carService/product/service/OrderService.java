package com.carService.product.service;

import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.repos.OrderRepo;
import com.carService.product.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    @Autowired
    private final OrderRepo orderRepo;
    @Autowired
    private final UserRepo userRepo;

//    public List<Order> listOrders(){
//        return orderRepo.findAll();
//    }

    public void saveOrder(Principal principal, Order order){
        order.setUser(getUserByPrincipal(principal));
        log.info("Saving new Order. {}" , order.getName());
        orderRepo.save(order);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }


    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }


    public List<Order> getOrdersForUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}
