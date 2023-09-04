package com.trustbycode.securitytoks.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
