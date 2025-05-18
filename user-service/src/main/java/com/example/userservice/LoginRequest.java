package com.example.userservice;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}