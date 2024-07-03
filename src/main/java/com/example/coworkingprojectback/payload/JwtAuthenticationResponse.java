package com.example.coworkingprojectback.payload;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, Collection<String> roles) {
        this.accessToken = accessToken;
    }
}
