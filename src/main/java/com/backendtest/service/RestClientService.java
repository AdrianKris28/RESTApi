package com.backendtest.service;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backendtest.dao.UserDao;
import com.backendtest.dto.Jobs;
import com.backendtest.dto.Users;
import com.backendtest.repository.UserRepository;

@Service
public class RestClientService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> getUserString(int id) {
        ResponseEntity<String> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/" + id,
                String.class);
        return response;
    }

    public ResponseEntity<Users> getUserObject(int id) {
        Users users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users/" + id, Users.class);
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Users[]> getAllUser() {
        ResponseEntity<Users[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users",
                Users[].class);
        return response;
    }

    public ResponseEntity<Users> postUserObject(Users users) {
        Users response = restTemplate.postForObject("https://jsonplaceholder.typicode.com/users", users, Users.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Jobs[]> getAllJobs() {
        ResponseEntity<Jobs[]> response = restTemplate
                .getForEntity("http://dev3.dansmultipro.co.id/api/recruitment/positions.json", Jobs[].class);
        return response;
    }

    public ResponseEntity<String> getJobs() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://dev3.dansmultipro.co.id/api/recruitment/positions.json", String.class);
        return response;
    }

    public ResponseEntity<Jobs> getJobsDetail(String id) {
        Jobs jobs = restTemplate.getForObject("http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id,
                Jobs.class);
        return ResponseEntity.ok(jobs);
    }

    public void createUser(UserDao user) {
        userRepository.save(user);
        
    }
}
