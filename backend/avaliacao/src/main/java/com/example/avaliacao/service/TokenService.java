package com.example.avaliacao.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final long EXPIRATION_DATE = 3600000;

    public String gerarToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername()).
                setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

}
