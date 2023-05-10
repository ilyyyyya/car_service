package com.carService.product.controller;

import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.repos.OrderRepo;
import com.carService.product.service.OrderService;
import com.carService.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;
    @Autowired
    private  OrderRepo orderRepo;
    @Autowired
    private UserService userService;



    @GetMapping("/orders")
    public String showOrders(Model model, Principal principal) {
        User currentUser = getCurrentUser(principal);
        List<Order> orders = orderRepo.findByUser(currentUser);
        model.addAttribute("orders", orders);
        return "orders";
    }

    private User getCurrentUser(Principal principal) {
        String username = principal.getName();
        return userService.findByUsername(username);
    }


    @GetMapping("/order")
    public String order() {
        return "order";
    }



    @PostMapping("/order/create")
    public String createOrder(Order order, Principal principal){
        orderService.saveOrder(principal,order);
        return "redirect:/";
    }

}
