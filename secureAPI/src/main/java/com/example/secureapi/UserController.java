package com.example.secureapi;


import com.example.secureapi.model.User;
import com.example.secureapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        if(!userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userRepository.deleteByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }


    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
