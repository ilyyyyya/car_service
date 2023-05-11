package com.carService.product.controller;


import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.entity.enums.Role;
import com.carService.product.repos.OrderRepo;
import com.carService.product.service.EmailService;
import com.carService.product.service.OrderService;
import com.carService.product.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



@Controller
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private EmailService emailService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users",userService.usersList());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }


    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam("selectedRoles") String[] selectedRoles) {
        userService.changeUserRoles(user, Arrays.asList(selectedRoles));
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/orders/{userid}")
    public String showOrdersForUser(@PathVariable("userid") Long userId, Model model) {
        List<Order> orders = orderService.getOrdersForUser(userId);
        model.addAttribute("ordersAdmin", orders);
        return "ordersAdmin";
    }

    @PostMapping("/admin/user/orders/{userid}/delete/{orderId}")
    public String deleteOrder(@PathVariable("userid") Long userId, @PathVariable("orderId") Long orderId) {

        orderService.deleteOrder(orderId);

        Order order = orderRepo.findOrderByIdAndUserId(orderId, userId);
        if (order == null) {
            return "redirect:/admin/user/orders/{userid}";
        }

        return "redirect:/admin/user/orders/{userid}";
    }




}
