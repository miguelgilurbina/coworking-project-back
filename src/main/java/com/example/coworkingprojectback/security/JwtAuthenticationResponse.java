package com.example.coworkingprojectback.security;

import java.util.Collection;

public class JwtAuthenticationResponse {

    private String token;


    public JwtAuthenticationResponse(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
