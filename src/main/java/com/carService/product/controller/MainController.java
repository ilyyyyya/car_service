package com.carService.product.controller;

import com.carService.product.repos.OrderRepo;
import com.carService.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;
@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String root() {
        return "main";
    }

}
