package com.example.demoBookShop.validators;

import com.example.demoBookShop.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation implements Validator<User>{
    @Override
    public boolean validation(User user) {

        if(user==null){
            return false;
        }else if(user.getFirstName().isBlank()){
            return false;
        }else if (user.getLastName().isBlank()){
            return false;
        }else if(user.getEmail().isBlank()){
            return false;
        }else if(testMatcher(user.getEmail(), "^(.+)@(.+)$")){
            return false;
        }else if(user.getRole().isBlank()){
            return false;
        }else if(user.getAddress().isBlank()){
            return false;
        }else if(user.getPhone().isBlank()){
            return false;
        }else if(testMatcher(user.getPhone(), "07\\d{8}")){
            return false;
        }else if(user.getCardNumber().isBlank()){
            return false;
        } else if(testMatcher(user.getCardNumber(), "\\d{16}$")){
            return false;
        }else if(user.getPassword().isBlank()){
            return false;
        }
            return true;
    }

    private boolean testMatcher(String str, String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }
}


