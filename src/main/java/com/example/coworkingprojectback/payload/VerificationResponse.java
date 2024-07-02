package com.example.coworkingprojectback.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VerificationResponse {
    private String email;
    private boolean isValid;

    public VerificationResponse(String emailUsuario, List<String> roles, boolean b) {
    }
}
