package com.example.demoBookShop.servicies;

import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllProducts(){
        return orderRepository.findAll();
    }

    public Order findProductById(Long id){
        return orderRepository.getById(id);
    }

    public Order create(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public void delete(Long id) {
        //also need to check for children records before deleting.
        orderRepository.deleteById(id);
    }

    public Order update(Long id, Order order) {
        //validation of all atributes
        Order existingOrder = findProductById(id);
        BeanUtils.copyProperties(order, existingOrder, "id_order");
        return orderRepository.saveAndFlush(existingOrder);
    }
}
