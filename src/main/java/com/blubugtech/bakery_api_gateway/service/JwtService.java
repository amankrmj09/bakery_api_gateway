package com.blubugtech.bakery_api_gateway.service;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import com.blubugtech.bakery_api_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtService {

    private static final String BEARER = "Bearer ";

    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> extractToken(ServerHttpRequest request) {

        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(BEARER)) {
            return Optional.empty();
        }

        return Optional.of(header.substring(BEARER.length()));
    }

    public boolean isValid(String token) {
        return jwtUtil.validateToken(token);
    }

    public AuthenticatedUser getAuthenticatedUser(String token) {

        Claims claims = jwtUtil.getClaims(token);

        String userId = claims.get("userId", String.class);

        if (userId == null) {
            userId = claims.getSubject();
        }

        String role = claims.get("role", String.class);

        return new AuthenticatedUser(userId, role);
    }
}
