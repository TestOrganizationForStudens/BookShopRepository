package com.example.demoBookShop.validators;

import com.example.demoBookShop.models.Order;

public class OrderValidation implements Validator<Order>{
    @Override
    public boolean validation(Order order) {
        if (order == null) {
            return false;
        } else if (order.getDateTime() == null) {
            return false;
        } else if (order.getPrice() == null) {
            return false;
        }else if(order.getProductOrderList()==null){
            return false;
        }else if(order.getProductOrderList().isEmpty()){
            return false;
        }
        return true;
    }
}
