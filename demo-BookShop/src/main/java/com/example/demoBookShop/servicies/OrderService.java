package com.example.demoBookShop.servicies;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.models.ProductOrder;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.repositories.OrderRepository;
import com.example.demoBookShop.repositories.ProductOrderRepository;
import com.example.demoBookShop.validators.OrderValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<Order> findByDateTime(LocalDateTime dateTime) throws AppException{
        if(dateTime==null){
            throw new AppException("DateTime is NULL");
        }
        return orderRepository.findByDateTime(dateTime);
    }

    public List<Order> findByUserData(User userData) throws AppException{
        if(userData==null){
            throw new AppException("User is NULL");
        }
        return orderRepository.findByUserData(userData);
    }

    public List<Order> findByPrice(Double price) throws AppException{
        if(price==null){
            throw new AppException("Price is NULL");
        }
        return orderRepository.findByPrice(price);
    }

    public Order findOrderById(Long id){
        return orderRepository.getById(id);
    }

    public Order create(Order order) throws AppException {
        orderValidation.validation(order);

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

    public Order delete(Long id) throws AppException{
        Order order = findOrderById(id);
        if(order==null){
            throw new AppException("There aren't such order id");
        }
        //also need to check for children records before deleting.
        orderRepository.deleteById(id);
        return order;
    }

    public Order update(Long id, Order order) throws AppException{
        //validation of all atributes
        orderValidation.validation(order);
        Order existingOrder = findOrderById(id);
        BeanUtils.copyProperties(order, existingOrder, "id_order");
        return orderRepository.saveAndFlush(existingOrder);
    }
}
