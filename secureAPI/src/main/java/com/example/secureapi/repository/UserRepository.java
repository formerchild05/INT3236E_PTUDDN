package com.example.secureapi.repository;

import com.example.secureapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}