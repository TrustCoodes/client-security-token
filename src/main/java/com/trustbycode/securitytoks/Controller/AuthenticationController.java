package com.trustbycode.securitytoks.Controller;

import com.trustbycode.securitytoks.Authentication.AuthRequest;
import com.trustbycode.securitytoks.Authentication.AuthResponse;
import com.trustbycode.securitytoks.Authentication.SignUpRequest;
import com.trustbycode.securitytoks.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/au/oauth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signupUser(signUpRequest));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authenticationService.authenticateUser(authRequest));
    }
}
