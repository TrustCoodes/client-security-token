package com.trustbycode.securitytoks.Service;

import com.trustbycode.securitytoks.Authentication.AuthRequest;
import com.trustbycode.securitytoks.Authentication.AuthResponse;
import com.trustbycode.securitytoks.Authentication.SignUpRequest;
import com.trustbycode.securitytoks.ClientEntities.Role;
import com.trustbycode.securitytoks.ClientEntities.User;
import com.trustbycode.securitytoks.Service.JwtServiceConfig.JwtService;
import com.trustbycode.securitytoks.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse signupUser(SignUpRequest signUpRequest) {
        var user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        var saveUser = repository.save(user);

        var tokenJwt = jwtService.generateToken(user);

        return AuthResponse.builder()
                .clientToken(tokenJwt)
                .build();
    }

    public AuthResponse authenticateUser(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        var user = repository.findByEmail(authRequest.getEmail()).orElseThrow();

        var tokenJwt = jwtService.generateToken(user);

        return AuthResponse.builder()
                .clientToken(tokenJwt)
                .build();
    }
}
