package com.example.farmBackend.Service;

import com.example.farmBackend.dto.impl.UserDTO;
import com.example.farmBackend.util.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailService();
    List<UserDTO> getAllUser();
    UserDTO getUserDetails(String email, Role role);
    UserDTO saveUser(UserDTO userDTO);
    void updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
}
