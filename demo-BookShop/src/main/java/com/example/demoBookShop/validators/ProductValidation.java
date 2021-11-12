package com.example.demoBookShop.validators;

import com.example.demoBookShop.models.Product;

public class ProductValidation implements Validator<Product>{
    @Override
    public boolean validation(Product product) {
        if(product==null){
            return false;
        }else if(product.getProductName().isBlank()){
            return false;
        }else if(product.getCategory().isBlank()){
            return false;
        }else if(product.getAuthor().isBlank()){
            return false;
        }else if(product.getPublishingHouse().isBlank()){
            return false;
        }else if(product.getYear()==null){
            return false;
        }else if (product.getPrice()==null){
            return false;
        }else if(product.getDescription().isBlank()){
            return false;
        }else if(product.getImage().isBlank()){
            return false;
        }
        return true;
    }
}
