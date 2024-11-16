package com.example.farmBackend.entity.impl;

import com.example.farmBackend.entity.Role;
import com.example.farmBackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class Users implements UserDetails, SuperEntity {
    @Id
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
