package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.AuthenticationService;
import com.example.farmBackend.Service.JWTService;
import com.example.farmBackend.auth.request.SignInRequest;
import com.example.farmBackend.auth.request.SignUpRequest;
import com.example.farmBackend.auth.response.JWTAuthResponse;
import com.example.farmBackend.dao.SecurityDAO;
import com.example.farmBackend.dao.UserDAO;
import com.example.farmBackend.dto.impl.UserDTO;
import com.example.farmBackend.entity.impl.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityDAO securityDAO;
    private final ModelMapper mapper;
    private final JWTService jwtService;
    private final UserDAO userDAO;
    @Override
    public JWTAuthResponse signIn(SignInRequest signInRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        // Fetch user details
        User user = securityDAO.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Generate tokens
        String accessToken = jwtService.generateToken(user);

        // Return both tokens
        return JWTAuthResponse.builder()
                .token(accessToken)
                .build();
    }

    @Override
    public JWTAuthResponse signUp(SignUpRequest signUpRequest) {
        UserDTO userDTO = UserDTO.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build();
        User savedUser = securityDAO.save(mapper.map(userDTO, User.class));

        // Generate tokens
        String accessToken = jwtService.generateToken(savedUser);

        return JWTAuthResponse.builder()
                .token(accessToken)
                .build();
    }
}
