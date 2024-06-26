package com.example.coworkingprojectback.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class ConstantesDeSeguridad {
    public static final long EXPIRACION_TOKEN_JWT = 3000000;
    public static final Key FIRMA_JWT = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static String stringKeyParser() {
        String base64EncodedKey = Base64.getEncoder().encodeToString(FIRMA_JWT.getEncoded());
        return base64EncodedKey;
    }
}