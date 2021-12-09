package com.example.demoBookShop.validators;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Product;

public class ProductValidation implements Validator<Product>{
    @Override
    public void validation(Product product) throws AppException {
        if(product==null){
            throw new AppException("Product is NULL");
        }else if(product.getProductName().isBlank()){
            throw new AppException("ProductName is Blank");
        }else if(product.getCategory().isBlank()){
            throw new AppException("Category is Blank");
        }else if(product.getAuthor().isBlank()){
            throw new AppException("Author is Blank");
        }else if(product.getPublishingHouse().isBlank()){
            throw new AppException("PublishHouse is Blank");
        }else if(product.getYear()==null){
            throw new AppException("Year is NULL");
        }else if (product.getPrice()==null){
            throw new AppException("Price is NULL");
        }else if(product.getDescription().isBlank()){
            throw new AppException("Description is Blank");
        }else if(product.getImage().isBlank()){
            throw new AppException("Image is Blank");
        }
    }
}
