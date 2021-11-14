package com.example.demoBookShop.controllers;


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
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user){
        if (userService.create(user)==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        User user=userService.delete(id);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(user);
        }
        //also need to check for children records before deleting.
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody User user){
        if (userService.update(id, user)==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

//    @PostConstruct
//    public void fillData() {
//     List<User> usersList= List.of(
//                                     new User("Andercou", "Alexandru", "andercou.alexandru@gmail.com", "Admin", "Cluj-Napoca , str Lalelelor, nr. 4", "0742512345", "1234123412341234", "password1"),
//                                     new User("Cioara", "Iulia", "cioara.iulia@gmail.com", "Admin", "Cluj-Napoca , str Islazului, nr. 41", "0742512345", "1234123412341234", "password2"),
//                                     new User("Lutencu", "Octavian", "lutencu.octavian@gmail.com", "Admin", "Cluj-Napoca , str Plopilor, nr. 1", "0742512345", "1234123412341234", "password2")
//     );
//     usersList.forEach(this.userService::create);
//    }
}
