package com.example.demoBookShop.controllers;


import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUser(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Object> findUserById(@PathVariable Long id){
        try{
            User user= userService.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not exist such user with this id="+id);
        }
    }

    @GetMapping("findUserByEmail")
    public ResponseEntity<Object> findUserByEmail(@RequestParam ("email") String email){
        try{
            User user=userService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("findUserByFirstName")
    public ResponseEntity<Object> findUserByFirstName(@RequestParam ("firstName") String firstName){
        try{
            User user=userService.findByFirstName(firstName);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("findUserByLastName")
    public ResponseEntity<Object> findUserByLastName(@RequestParam ("lastName") String lastName){
        try{
            User user=userService.findByLastName(lastName);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("findUserByUserName")
    public ResponseEntity<Object> findUserByUserName(@RequestParam ("userName") String userName){
        try{
            User user=userService.findByUserName(userName);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        try{
            userService.create(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try{
            //also need to check for children records before deleting.
            User user=userService.delete(id);
            return  ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user){
        try{
            userService.update(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
    }

//    @PostConstruct
//    public void fillData() {
//     List<User> usersList= List.of(
//                                     new User("Andercou", "Alexandru", "andercou.alexandru@gmail.com", "Admin", "Cluj-Napoca , str Lalelelor, nr. 4", "0742512345", "1234123412341234", "password1", "Alex"),
//                                     new User("Cioara", "Iulia", "cioara.iulia@gmail.com", "Admin", "Cluj-Napoca , str Islazului, nr. 41", "0742512345", "1234123412341234", "password2", "Iulea"),
//                                     new User("Lutencu", "Octavian", "lutencu.octavian@gmail.com", "Admin", "Cluj-Napoca , str Plopilor, nr. 1", "0742512345", "1234123412341234", "password2", "Octav")
//     );
//     usersList.forEach(this.userService::create);
//    }
}
