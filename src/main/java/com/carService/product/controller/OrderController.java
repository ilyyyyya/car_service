package com.carService.product.controller;

import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.repos.OrderRepo;
import com.carService.product.repos.UserRepo;
import com.carService.product.service.EmailService;
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
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;



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
    public String createOrder(Order order, Principal principal) {

        orderService.saveOrder(principal,order);

//        String username = principal.getName();
//        User user = userRepo.findByEmail(username);
//        if (user == null) {
//            return "redirect:/error";
//        }
//        String subject = "Создание нового заказа";
//        String text = "Уважаемый " + user.getName() + ",\n\n" +
//                "Ваш заказ #" + order.getId() + " был успешно создан.";
//
//        emailService.emailSender(username, subject, text);

        return "redirect:/";

    }

}
