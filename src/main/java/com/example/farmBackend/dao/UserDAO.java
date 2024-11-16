package com.example.farmBackend.dao;

import com.example.farmBackend.entity.impl.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<Users, String> {
    Optional<Users> findByEmail(String email);
}
