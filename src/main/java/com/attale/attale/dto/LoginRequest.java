package com.attale.attale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message="Email is Required")
    private String email;
    @NotBlank(message="Password is Required")
    private String password;
}
