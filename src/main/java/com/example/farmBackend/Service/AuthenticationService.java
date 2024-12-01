package com.example.farmBackend.Service;


import com.example.farmBackend.auth.request.SignInRequest;
import com.example.farmBackend.auth.request.SignUpRequest;
import com.example.farmBackend.auth.response.JWTAuthResponse;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignInRequest signInRequest);
    JWTAuthResponse signUp(SignUpRequest signUpRequest);
//    JWTAuthResponse refreshToken(String accessToken);
}
