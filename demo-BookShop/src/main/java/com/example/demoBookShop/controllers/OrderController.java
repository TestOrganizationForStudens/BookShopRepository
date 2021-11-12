package com.example.demoBookShop.controllers;

import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.servicies.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllProducts(){
        return orderService.getAllProducts();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Order findProductById(@PathVariable Long id){
        return orderService.findProductById(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //also need to check for children records before deleting.
        orderService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Order update(@PathVariable Long id, @RequestBody Order order){
        return  orderService.update(id, order);
    }
}
