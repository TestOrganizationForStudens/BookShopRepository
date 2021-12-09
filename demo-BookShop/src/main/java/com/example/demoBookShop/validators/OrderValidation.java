package com.example.demoBookShop.validators;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Order;

public class OrderValidation implements Validator<Order>{
    @Override
    public void validation(Order order) throws AppException {
        if (order == null) {
            throw new AppException("Order is NULL");
        } else if (order.getDateTime() == null) {
            throw new AppException("Time and date don't set");
        } else if (order.getPrice() == null) {
            throw new AppException("Price doesn't set");
        }else if(order.getProductOrderList()==null){
            throw new AppException("Product list is NULL, please select product");
        }else if(order.getProductOrderList().isEmpty()){
            throw new AppException("Please select product");
        }
    }
}
