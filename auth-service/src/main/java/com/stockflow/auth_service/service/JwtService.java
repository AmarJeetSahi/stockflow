package com.stockflow.auth_service.service;

import com.stockflow.auth_service.dto.LoginRequestDto;
import com.stockflow.auth_service.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(LoginRequestDto userDetails) {

        return Jwts.builder()
                .subject(userDetails.getEmail())
                .claim("password", userDetails.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()
                        +expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }
}
