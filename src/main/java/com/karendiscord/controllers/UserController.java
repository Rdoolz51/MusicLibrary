package com.karendiscord.controllers;

import com.karendiscord.models.User;
import com.karendiscord.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public User getUserByIdHandler(@PathVariable int id){
        return userService.getUserById(id);
    }
}
