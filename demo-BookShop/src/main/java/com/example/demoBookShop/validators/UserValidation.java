package com.example.demoBookShop.validators;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation implements Validator<User>{
    @Override
    public void validation(User user) throws AppException {

        if(user==null){
            throw new AppException("User is null");
        }else if(user.getFirstName().isBlank()){
            throw new AppException("Field firstName is empty");
        }else if (user.getLastName().isBlank()){
            throw new AppException("Field lastName is empty");
        }else if(user.getEmail().isBlank()){
            throw new AppException("Field email is empty");
        }else if(testMatcher(user.getEmail(), "^(.+)@(.+)$")){
            throw new AppException("String is not email pattern");
        }else if(user.getRole().isBlank()){
            throw new AppException("Field role is empty");
        }else if(user.getAddress().isBlank()){
            throw new AppException("Field address is empty");
        }else if(user.getPhone().isBlank()){
            throw new AppException("Field phone is empty");
        }else if(testMatcher(user.getPhone(), "07\\d{8}")){
            throw new AppException("String is not phone pattern");
        }else if(user.getCardNumber().isBlank()){
            throw new AppException("Field cardNumber is empty");
        } else if(testMatcher(user.getCardNumber(), "\\d{16}$")){
            throw new AppException("String is not cardNumber pattern");
        }else if(user.getPassword().isBlank()){
            throw new AppException("Field password is empty");
        }
    }

    private boolean testMatcher(String str, String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }
}


