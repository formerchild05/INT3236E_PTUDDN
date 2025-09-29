package com.example.secureapi;

import com.example.secureapi.model.Role;
import com.example.secureapi.model.User;
import com.example.secureapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin account created: admin/123456");
            }
            if (userRepository.findByUsername("abc") == null) {
                User abc = new User();
                abc.setUsername("abc");
                abc.setPassword(passwordEncoder.encode("123"));
                abc.setRole(Role.USER);
                userRepository.save(abc);
            }
        };
    }
}
