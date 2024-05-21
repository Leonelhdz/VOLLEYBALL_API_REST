package com.example.volleyball_api_rest;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "t0k3n";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
