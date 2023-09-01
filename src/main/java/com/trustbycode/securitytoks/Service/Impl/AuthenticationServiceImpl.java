package com.trustbycode.securitytoks.Service.Impl;

import com.trustbycode.securitytoks.Authentication.AuthRequest;
import com.trustbycode.securitytoks.Authentication.AuthResponse;
import com.trustbycode.securitytoks.Authentication.SignUpRequest;
import com.trustbycode.securitytoks.Client.Role;
import com.trustbycode.securitytoks.Config.JwtService;
import com.trustbycode.securitytoks.Repository.UserRepository;
import com.trustbycode.securitytoks.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService service;

    @Override
    public AuthResponse signupUser(SignUpRequest signUpRequest) {
        var user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        var token = service.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        return null;
    }
}
