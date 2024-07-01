package com.example.coworkingprojectback.security;

import java.util.Collection;

public class JwtAuthenticationResponse {

    private String token;
    private Collection<String> roles;

    public JwtAuthenticationResponse(String token, Collection<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
