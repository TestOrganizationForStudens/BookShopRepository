package com.example.demoBookShop.controllers;

import com.example.demoBookShop.models.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {

    @GetMapping
    public String getAllOrders() {
        return "SALUTTTTT!!";
    }
}
