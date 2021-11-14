package com.example.demoBookShop.servicies;

import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.models.ProductOrder;
import com.example.demoBookShop.repositories.OrderRepository;
import com.example.demoBookShop.repositories.ProductOrderRepository;
import com.example.demoBookShop.validators.OrderValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final OrderValidation orderValidation=new OrderValidation();

    public OrderService(OrderRepository orderRepository, ProductOrderRepository productOrderRepository) {
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id){
        return orderRepository.getById(id);
    }

    public Order create(Order order) {
        if(!orderValidation.validation(order)){
            return null;//error
        }
        List<ProductOrder> productOrderList=order.getProductOrderList();
        order.setProductOrderList(null);
        Order createdOrder=orderRepository.saveAndFlush(order);

//        for(int i=0; i< productOrderList.size(); ++i){
//            productOrderList.get(i).setOrder(createdOrder);
//                    productOrderRepository.saveAndFlush(productOrderList.get(i));
//        }

        productOrderList.forEach(x->{
                    x.setOrder(createdOrder);
                    productOrderRepository.saveAndFlush(x);
                });
        return createdOrder;
    }

    public Order delete(Long id) {
        Order order = findOrderById(id);
        if(order==null){
            return null;
        }
        //also need to check for children records before deleting.
        orderRepository.deleteById(id);
        return order;
    }

    public Order update(Long id, Order order) {
        //validation of all atributes
        if(!orderValidation.validation(order)){
            return null;//error
        }
        Order existingOrder = findOrderById(id);
        BeanUtils.copyProperties(order, existingOrder, "id_order");
        return orderRepository.saveAndFlush(existingOrder);
    }
}
