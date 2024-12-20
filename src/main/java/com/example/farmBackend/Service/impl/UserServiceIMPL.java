package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.UserService;
import com.example.farmBackend.dao.UserDAO;
import com.example.farmBackend.dto.impl.UserDTO;
import com.example.farmBackend.entity.impl.User;
import com.example.farmBackend.exception.DuplicateRecordException;
import com.example.farmBackend.exception.NotFoundException;
import com.example.farmBackend.util.Role;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    UserDAO userDAO;
    ModelMapper modelMapper;

    public UserServiceIMPL(UserDAO userDAO, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetailsService userDetailService() {
        return username -> userDAO.findByEmail(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException(
                        "user not found"));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userDAO.findAll().stream().map(
                user -> modelMapper.map(user, UserDTO.class)
        ).toList();
    }

    @Override
    public UserDTO getUserDetails(String email, Role role) {
        if (!userDAO.existsByEmail(email)) {
            throw new NotFoundException("User email :" + email + " Not Found!");
        }
        return modelMapper.map(userDAO.findByEmailAndRole(email, role), UserDTO.class);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDAO.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateRecordException("This User " + userDTO.getEmail() + " already have an account.");
        }
        return modelMapper.map(userDAO.save(modelMapper.map(
                userDTO, User.class)), UserDTO.class
        );
    }

    @Override
    public void updateUser(String email, UserDTO userDTO) {
        User existingUser = userDAO.findByEmailAndRole(email, userDTO.getRole());

        if (existingUser.getPassword().isEmpty()) {
            throw new NotFoundException("User email :" + email + "Not Found...");
        }

        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        userDAO.save(existingUser);
    }

    @Override
    public void deleteUser(String email) {
        if (!userDAO.existsByEmail(email)) {
            throw new NotFoundException("User email :" + email + "Not Found...");
        }
        userDAO.deleteByEmail(email);
    }
}