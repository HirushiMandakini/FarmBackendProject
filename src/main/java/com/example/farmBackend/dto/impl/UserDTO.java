package com.example.farmBackend.dto.impl;

import com.example.farmBackend.dto.UserStatus;
import com.example.farmBackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePic;
    private Role role;
}
