package com.carService.product.controller;

import com.carService.product.entity.Order;
import com.carService.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public String orders(@RequestParam(name = "name", required = false) String name,Principal principal, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("orders", orderService.listOrders(name));
        redirectAttributes.addAttribute("user", orderService.getUserByPrincipal(principal));
        return "orders";
    }

    @GetMapping("/order")
    public String order() {
        return "order";
    }

//    @GetMapping("/order/{id}")
//    public String orderInfo(@PathVariable Long id, Model model) {
//        Order order = orderService.getOrderById(id);
//        model.addAttribute("product", order);
//        return "order-info";
//    }

    @PostMapping("/order/create")
    public String createOrder(Order order, Principal principal){
        orderService.saveOrder(principal,order);
        return "redirect:/";
    }
    @PostMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/";
    }
}
