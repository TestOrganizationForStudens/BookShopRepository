package com.example.demoBookShop.servicies;

import com.example.demoBookShop.exceptions.AppException;
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

    public User findUserById(Long id) throws AppException {
        try{
            User user= userRepository.getById(id);
            return user;
        }catch (Exception ex){
            throw  new AppException("There isn't such user with this id="+id);
        }
    }

    public User findByEmail(String email) throws AppException{
        if(email.isBlank())
            throw new AppException("Field email is empty");
        User user=userRepository.findByEmail(email);
        if(user==null)
            throw new AppException("Not exist such user with this email="+email);
        return user;
    }

    public User findByFirstName(String firstName) throws AppException{
        if(firstName.isBlank())
            throw new AppException("Field firstName is empty");
        User user= userRepository.findByFirstName(firstName);
        if(user==null)
            throw new AppException("Not exist such user with this firstName="+firstName);
        return user;
    }

    public User findByLastName(String lastName) throws AppException{
        if(lastName.isBlank())
            throw new AppException("Field lastName is empty");
        User user= userRepository.findByLastName(lastName);
        if(user==null)
            throw new AppException("Not exist such user with this lastName="+lastName);
        return user;
    }

    public User findByUserName(String userName) throws AppException{
        if(userName.isBlank())
            throw new AppException("Field userName is empty");
        User user= userRepository.findByUserName(userName);
        if(user==null)
            throw new AppException("Not exist such user with this userName="+userName);
        return user;
    }

    public User create(User user) throws AppException{
        userValidation.validation(user);
        return userRepository.saveAndFlush(user);
    }

    public User delete(Long id) throws AppException {
        User user=null;
        try{
            //also need to check for children records before deleting.
            user= findUserById(id);
            userRepository.deleteById(id);
        }catch (Exception ex){
            throw new AppException("There isn't such user with this id="+id);
        }
        return user;
    }

    public User update(Long id, User user) throws AppException {
        //validation of all atributes
        userValidation.validation(user);
        User existingUser = findUserById(id);
        if(existingUser==null){
            throw new AppException("There isn't such user with this id="+id);
        }
        BeanUtils.copyProperties(user, existingUser, "id_user");
        return userRepository.saveAndFlush(existingUser);
    }

}
