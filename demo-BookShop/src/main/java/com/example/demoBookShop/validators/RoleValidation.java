package com.example.demoBookShop.validators;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Role;

public class RoleValidation implements Validator<Role>{
    @Override
    public void validation(Role role) throws AppException {
        if(role==null){
            throw new AppException("Role is null");
        }else if(role.getRole().isBlank()) {
            throw new AppException("Field role is empty");
        }
    }
}
