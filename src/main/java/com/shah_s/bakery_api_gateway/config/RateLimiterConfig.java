package com.shah_s.bakery_api_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RateLimiterConfig {

    @Bean
    @Primary
    public KeyResolver hybridKeyResolver() {
        return exchange -> {
            // First check if the request is authenticated (AuthenticationFilter sets X-User-Id)
            String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");
            if (userId != null && !userId.isEmpty()) {
                return Mono.just("user:" + userId);
            }

            // Fallback to IP address for public/unauthenticated endpoints
            String ipAddress = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
            return Mono.just("ip:" + ipAddress);
        };
    }
}
