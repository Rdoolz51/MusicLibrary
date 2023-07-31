package com.karendiscord.controllers;


import com.karendiscord.dtos.AuthDTO;
import com.karendiscord.dtos.LoginDTO;
import com.karendiscord.dtos.RegisterDTO;
import com.karendiscord.models.User;
import com.karendiscord.repositories.UserRepository;
import com.karendiscord.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://dooleybootpipeline-env.eba-kukpuwjp.us-east-2.elasticbeanstalk.com/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username already in use! ", HttpStatus.BAD_REQUEST);
        }

        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword())
        );
        userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );

        String token = tokenGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthDTO(token), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(
                new AuthDTO(tokenGenerator.generateToken(authentication)),
                HttpStatus.OK
        );
    }
}
