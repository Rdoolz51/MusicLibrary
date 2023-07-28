package com.karendiscord.controllers;

import com.karendiscord.models.User;
import com.karendiscord.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
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
    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username){
        return userService.getByUsername(username);
    }
}
