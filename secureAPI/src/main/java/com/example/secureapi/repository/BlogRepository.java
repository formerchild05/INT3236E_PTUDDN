package com.example.secureapi.repository;

import com.example.secureapi.model.Blog;
import com.example.secureapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByUserUsername(String username);

    String user(User user);


}