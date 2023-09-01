package com.trustbycode.securitytoks.Service;

import com.trustbycode.securitytoks.Authentication.AuthRequest;
import com.trustbycode.securitytoks.Authentication.AuthResponse;
import com.trustbycode.securitytoks.Authentication.SignUpRequest;

public interface AuthenticationService {

    AuthResponse signupUser(SignUpRequest signUpRequest);

    AuthResponse authenticateUser(AuthRequest authRequest);
}
