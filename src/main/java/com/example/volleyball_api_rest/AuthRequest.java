package com.example.volleyball_api_rest;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
