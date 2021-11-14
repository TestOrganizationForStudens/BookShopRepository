package com.example.demoBookShop.servicies;

import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.repositories.UserRepository;
import com.example.demoBookShop.validators.UserValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserValidation userValidation=new UserValidation();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.getById(id);
    }

    public User create(User user) {
        if(!userValidation.validation(user)){
            return null;//error
        }
        return userRepository.saveAndFlush(user);
    }

    public User delete(Long id) {
        User user= findUserById(id);
        if(user==null){
            return null;
        }
        //also need to check for children records before deleting.
        userRepository.deleteById(id);
        return user;
    }

    public User update(Long id, User user) {
        //validation of all atributes
        if(!userValidation.validation(user)){
            return null;//error
        }
        User existingUser = findUserById(id);
        BeanUtils.copyProperties(user, existingUser, "id_user");
        return userRepository.saveAndFlush(existingUser);
    }

}
