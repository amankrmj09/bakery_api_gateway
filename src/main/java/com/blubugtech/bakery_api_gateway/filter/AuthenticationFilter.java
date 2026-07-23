package com.blubugtech.bakery_api_gateway.filter;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import com.blubugtech.bakery_api_gateway.service.HeaderService;
import com.blubugtech.bakery_api_gateway.service.JwtService;
import com.blubugtech.bakery_api_gateway.service.PublicEndpointService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtService jwtService;
    private final PublicEndpointService publicEndpointService;
    private final HeaderService headerService;

    public AuthenticationFilter(
            JwtService jwtService,
            PublicEndpointService publicEndpointService,
            HeaderService headerService
    ) {
        super(Config.class);
        this.jwtService = jwtService;
        this.publicEndpointService = publicEndpointService;
        this.headerService = headerService;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            if (publicEndpointService.isPublic(request)) {
                return jwtService.extractToken(request)
                        .filter(jwtService::isValid)
                        .map(jwtService::getAuthenticatedUser)
                        .map(user -> authenticate(exchange, chain, user))
                        .orElseGet(() -> chain.filter(exchange));
            }

            return jwtService.extractToken(request)
                    .filter(jwtService::isValid)
                    .map(jwtService::getAuthenticatedUser)
                    .map(user -> authenticate(exchange, chain, user))
                    .orElseGet(() -> unauthorized(exchange));
        };
    }

    private Mono<Void> authenticate(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain,
            AuthenticatedUser user
    ) {

        ServerHttpRequest request =
                headerService.addAuthenticatedHeaders(exchange.getRequest(), user);

        return chain.filter(exchange.mutate().request(request).build());
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
    }
}
