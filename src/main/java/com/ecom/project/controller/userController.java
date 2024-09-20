package com.ecom.project.controller;

import com.ecom.project.model.User;
import com.ecom.project.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class userController {

    @Autowired
    private userService service;

    @PostMapping("/register")
    public User registerNewUser(@RequestBody User user) {
        return service.registerNewUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return service.verify(user);
    }
}
