package com.blubugtech.bakery_api_gateway.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class PublicEndpointService {

    public boolean isPublic(ServerHttpRequest request) {

        String path = request.getURI().getPath();
        HttpMethod method = request.getMethod();

        if (method == HttpMethod.GET) {
            return path.startsWith("/api/products")
                    || path.startsWith("/api/categories")
                    || path.startsWith("/api/uploads/media")
                    || path.startsWith("/api/store/settings")
                    || path.startsWith("/swagger-ui")
                    || path.startsWith("/v3/api-docs")
                    || path.startsWith("/webjars");
        }

        return path.startsWith("/api/carts")
                || path.startsWith("/api/cart-items")
                || path.startsWith("/api/auth/login")
                || path.startsWith("/api/auth/register")
                || path.startsWith("/api/auth/refresh")
                || path.startsWith("/api/auth/validate")
                || path.startsWith("/api/auth/health");
    }
}
