package com.carService.product.controller;

import com.carService.product.entity.AutoPart;
import com.carService.product.service.AutoPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AutoPartController {

    @Autowired
    AutoPartService autoPartService;


}
