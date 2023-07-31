package com.karendiscord.controllers;

import com.karendiscord.dtos.PasswordChangeDTO;
import com.karendiscord.dtos.UserDTO;
import com.karendiscord.models.User;
import com.karendiscord.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://dooleybootpipeline-env.eba-kukpuwjp.us-east-2.elasticbeanstalk.com/")
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

    @PutMapping("{id}")
    public User updateUserHandler(@PathVariable int id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PostMapping("/{id}/change-password")
    public User changePasswordHandler(@PathVariable int id, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        return userService.changePassword(id, passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());
    }
}
