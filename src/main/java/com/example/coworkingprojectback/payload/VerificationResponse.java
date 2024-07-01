package com.example.coworkingprojectback.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationResponse {
    private String email;
    private boolean isValid;
}
