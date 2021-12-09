package com.example.demoBookShop.validators;

import com.example.demoBookShop.exceptions.AppException;

public interface Validator<T> {

    void validation(T t) throws AppException;
}
