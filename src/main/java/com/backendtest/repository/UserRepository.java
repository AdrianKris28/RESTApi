package com.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendtest.dao.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Long> {
    UserDao findByUsername(String username);
}
