package com.sample.ecommerce.controller;

// import com.sample.ecommerce.model.Item;
//import com.sample.ecommerce.repository.UserRepo;

import com.sample.ecommerce.model.User;
import com.sample.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping(path="/{emailId}") // Map ONLY GET Requests
    public @ResponseBody
    ResponseEntity<User> findUser(@PathVariable(value ="emailId" ) String emailId) {
        return userService.findUser(emailId);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}