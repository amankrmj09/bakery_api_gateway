package com.shah_s.bakery_api_gateway.filter;

import com.shah_s.bakery_api_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            String path = request.getURI().getPath();
            org.springframework.http.HttpMethod method = request.getMethod();

            boolean isPublicEndpoint = 
                (method == org.springframework.http.HttpMethod.GET && (path.startsWith("/api/products") || path.startsWith("/api/categories") || path.startsWith("/api/uploads/media"))) ||
                path.startsWith("/api/carts") || 
                path.startsWith("/api/cart-items") ||
                path.contains("/api/auth/login") ||
                path.contains("/api/auth/register");
            
            // If no auth header, check if it's a public endpoint
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                if (isPublicEndpoint) {
                    return chain.filter(exchange);
                }
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            Claims claims = jwtUtil.getClaims(token);
            
            // The subject is the username, but downstream services expect X-User-Id to be a UUID.
            // The Auth service sets the actual UUID in the "userId" claim.
            String userId = claims.get("userId", String.class);
            if (userId == null) {
                // Fallback to subject if userId claim is missing
                userId = claims.getSubject();
            }
            String role = claims.get("role", String.class);

            // Remove any incoming spoofed headers, then add the trusted ones
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .headers(httpHeaders -> {
                        httpHeaders.remove("X-User-Id");
                        httpHeaders.remove("X-User-Role");
                    })
                    .header("X-User-Id", userId)
                    .header("X-User-Role", role)
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    public static class Config {
        // Configuration properties can be added here if needed
    }
}
