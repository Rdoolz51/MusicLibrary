package com.karendiscord.services;

import com.karendiscord.dtos.UserDTO;
import com.karendiscord.exceptions.ResourceNotFoundException;
import com.karendiscord.exceptions.UsernameAlreadyExistsException;
import com.karendiscord.models.User;
import com.karendiscord.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }


        public User updateUser(int id, UserDTO userDTO) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

            if(userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
                    user.setFirstName(userDTO.getFirstName());
            }

            if(userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
                user.setLastName(userDTO.getLastName());
            }

            if(userDTO.getUsername() != null && !userDTO.getUsername().isEmpty()) {
                if(!userRepository.existsByUsername(userDTO.getUsername())) {
                    user.setUsername(userDTO.getUsername());
                } else {
                    throw new UsernameAlreadyExistsException("Username already exists.");
                }
            }

            userRepository.save(user);

            return user;
    }

    public User changePassword(int id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password cannot match old password!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return user;
    }
}
