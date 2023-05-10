package com.carService.product.controller;


import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.entity.enums.Role;
import com.carService.product.repos.OrderRepo;
import com.carService.product.service.OrderService;
import com.carService.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

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




}
