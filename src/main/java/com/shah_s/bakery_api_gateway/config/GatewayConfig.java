package com.shah_s.bakery_api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Auth Service Routes
                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .uri("lb://auth-service"))

                // Auth Service Routes - User Management endpoints
                .route("auth-service-users", r -> r
                        .path("/api/users/**")
                        .uri("lb://auth-service"))

                // Product Service Routes - Categories
                .route("product-service-categories", r -> r
                        .path("/api/categories/**")
                        .uri("lb://product-service"))

                // Product Service Routes - Products
                .route("product-service-products", r -> r
                        .path("/api/products/**")
                        .uri("lb://product-service"))

                // Product Service Routes - Inventory
                .route("product-service-inventory", r -> r
                        .path("/api/inventory/**")
                        .uri("lb://product-service"))

                // Order Service Routes
                .route("order-service", r -> r
                        .path("/api/orders/**")
                        .uri("lb://order-service"))

                // Order Service Routes - Orders
                .route("order-service-orders", r -> r
                        .path("/api/orders/**")
                        .uri("lb://order-service"))

                // Payment Service Routes - Payments
                .route("payment-service-payments", r -> r
                        .path("/api/payments/**")
                        .uri("lb://payment-service"))

                // Payment Service Routes - Refunds
                .route("payment-service-refunds", r -> r
                        .path("/api/refunds/**")
                        .uri("lb://payment-service"))

                // Payment Service Routes - Transactions
                .route("payment-service-transactions", r -> r
                        .path("/api/transactions/**")
                        .uri("lb://payment-service"))

                // Cart Service Routes - Carts
                .route("cart-service-carts", r -> r
                        .path("/api/carts/**")
                        .uri("lb://cart-service"))

                // Cart Service Routes - Cart Items
                .route("cart-service-items", r -> r
                        .path("/api/cart-items/**")
                        .uri("lb://cart-service"))

                // Notification Service Routes
                .route("notification-service", r -> r
                        .path("/api/notifications/**")
                        .uri("lb://notification-service"))

                // Health check route for debugging
                .route("gateway-health", r -> r
                        .path("/gateway/health")
                        .uri("http://localhost:8080/actuator/health"))

                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedOriginPattern("*"); // Configure specific origins in production
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
