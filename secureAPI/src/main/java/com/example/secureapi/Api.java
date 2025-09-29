package com.example.secureapi;

import com.example.secureapi.model.Blog;
import com.example.secureapi.model.User;
import com.example.secureapi.repository.BlogRepository;
import com.example.secureapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@PreAuthorize("hasRole('USER')")
public class Api {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<Blog>> getBlogs(Authentication auth) {
        String username = auth.getName();
        List<Blog> blogs = blogRepository.findAllByUserUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(blogs);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(blog);
        }
        try {
            blog.setUser(user);
            blogRepository.save(blog);
            return ResponseEntity.status(HttpStatus.CREATED).body(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(blog);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id,@RequestBody Blog blog, Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        Blog newBlog = blogRepository.findById(id).orElse(null);


        if (blog == null || !blog.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid blog");
        }
        newBlog.setTitle(blog.getTitle());
        newBlog.setAuthor(blog.getAuthor());

        try {
            blogRepository.save(blog);
            return ResponseEntity.status(HttpStatus.OK).body(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(blog);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BLOG NOT FOUND");
        }

        if (!blog.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("cANNOT DELETE BLOG");
        }

        blogRepository.delete(blog);
        return ResponseEntity.status(HttpStatus.OK).body(blog);

    }

}
