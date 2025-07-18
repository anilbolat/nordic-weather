package com.anilbolat.nordicweather.endpoint.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
