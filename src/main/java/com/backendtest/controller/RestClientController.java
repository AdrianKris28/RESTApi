package com.backendtest.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backendtest.dto.Users;
import com.backendtest.service.RestClientService;

@Controller
@RequestMapping("/api/users")
public class RestClientController {

    @Autowired
    private RestClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<String> findUserStringById(@PathVariable("id") int id) {
        return service.getUserString(id);
    }

    @GetMapping("/object/{id}")
    public ResponseEntity<Users> findUserObjectById(@PathVariable("id") int id) {
        return service.getUserObject(id);
    }

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        return service.getAllUser();
    }

    @PostMapping
    public ResponseEntity<Users> postUser(@RequestBody Users users) {
        return service.postUserObject(users);
    }

}
