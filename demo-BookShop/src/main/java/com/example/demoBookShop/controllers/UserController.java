package com.example.demoBookShop.controllers;


import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Role;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.repositories.RoleRepository;
import com.example.demoBookShop.servicies.UserService;
import com.example.demoBookShop.utility.AuthenticationRequest;
import com.example.demoBookShop.utility.AuthenticationResponse;
import com.example.demoBookShop.utility.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping(path="/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final Jwt jwt;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, Jwt jwt) {
        this.userService = userService;
        this.authenticationManager=authenticationManager;
        this.jwt=jwt;
    }

    @PostMapping(path="/login")
    public ResponseEntity<Object> authentificationUser(@RequestBody AuthenticationRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        final UserDetails userDetails=userService.loadUserByUsername(authRequest.getUserName());
        final String jwtToken=jwt.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @GetMapping(path="/all")
    @CrossOrigin(origins = "https://localchost:4200")
    public ResponseEntity<Object> getAllUser(){
        List<User> users=userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping
    @RequestMapping("/all{id}")
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

    @PostMapping(path="/add")
    public ResponseEntity<Object> createUser(@RequestBody User user){//, @RequestBody Role role){
        try{
            Role role = new Role("ROLE_USER");
            userService.create(user, role);
            URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
            return ResponseEntity.created(uri).body(user);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try{
            //also need to check for children records before deleting.
            User user=userService.delete(id);
            return  ResponseEntity.ok().body(user);
        }catch (AppException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
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
//        List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");
//        roles.forEach(this.userService::createRole);
//        Role adminRole = new Role("ROLE_ADMIN");
//
//        List<User> usersList = List.of(
//                new User("Andercou", "Alexandru", "andercou.alexandru@gmail.com", "Cluj-Napoca , str Lalelelor, nr. 4", "0742512345", "1234123412341234", "password1", "Alex"),
//                new User("Cioara", "Iulia", "cioara.iulia@gmail.com", "Cluj-Napoca , str Islazului, nr. 41", "0742512345", "1234123412341234", "password2", "Iulea"),
//                new User("Lutencu", "Octavian", "lutencu.octavian@gmail.com", "Cluj-Napoca , str Plopilor, nr. 1", "0742512345", "1234123412341234", "password2", "Octav")
//        );
//        usersList.forEach(user -> {
//            try {
//                userService.create(user, adminRole);
//            } catch (AppException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
