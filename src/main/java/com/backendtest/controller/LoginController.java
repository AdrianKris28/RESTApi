package com.backendtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backendtest.dao.UserDao;
import com.backendtest.repository.UserRepository;
import com.backendtest.service.RestClientService;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestClientService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDao user) {
        UserDao existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        return ResponseEntity.ok("Login Successful");
    }

    @PostMapping("/create")
    public ResponseEntity<UserDao> createUser(@RequestBody UserDao user) {

        service.createUser(user);
        // Return the saved user with a 201 CREATED status code
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
