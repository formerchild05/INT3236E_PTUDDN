package com.example.secureapi;


import com.example.secureapi.model.Role;
import com.example.secureapi.model.User;
import com.example.secureapi.repository.UserRepository;
import com.example.secureapi.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class Auth {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public Auth(UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.userRepository = userRepo;
        this.passwordEncoder = encoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() == null) user.setRole(Role.USER);
        try {
            userRepository.save(user);
            return "User registered!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            final String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(Map.of("token", token));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
            }
    }
}
