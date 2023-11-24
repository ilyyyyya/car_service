package com.carService.product.controller;


import com.carService.product.entity.AutoPart;
import com.carService.product.service.AutoPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    AutoPartService autoPartService;

    @GetMapping("/")
    public String root(Principal principal, Model model) {
        if(principal != null){
            String username = principal.getName();
            model.addAttribute("username",username);
        }
        List<AutoPart> autoParts = autoPartService.getAllAutoParts();
        model.addAttribute("autoParts",autoParts);
        return "main";
    }



}
