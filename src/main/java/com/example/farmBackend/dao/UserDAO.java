package com.example.farmBackend.dao;

import com.example.farmBackend.entity.impl.User;
import com.example.farmBackend.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,String> {
    Boolean existsByEmail(String email);
    User findByEmailAndRole(String email, Role role);
    void deleteByEmail(String email);
    Optional<User> findByEmail(String email);
}
