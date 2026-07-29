package com.blubugtech.bakery_api_gateway.service;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import com.blubugtech.bakery_api_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import com.blubugtech.bakery_api_gateway.constant.GatewayConstants;

import java.util.Optional;

@Service
public class JwtService {


    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> extractToken(ServerHttpRequest request) {

        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(GatewayConstants.BEARER_PREFIX)) {
            return Optional.empty();
        }

        return Optional.of(header.substring(GatewayConstants.BEARER_PREFIX.length()));
    }

    public boolean isValid(String token) {
        return jwtUtil.validateToken(token);
    }

    public AuthenticatedUser getAuthenticatedUser(String token) {

        Claims claims = jwtUtil.getClaims(token);

        String userId = claims.get(GatewayConstants.JWT_CLAIM_USER_ID, String.class);

        if (userId == null) {
            userId = claims.getSubject();
        }

        String role = claims.get(GatewayConstants.JWT_CLAIM_ROLE, String.class);
        String email = claims.get(GatewayConstants.JWT_CLAIM_EMAIL, String.class);

        return new AuthenticatedUser(userId, role, email);
    }
}
