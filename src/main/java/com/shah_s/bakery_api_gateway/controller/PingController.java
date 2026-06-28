package com.shah_s.bakery_api_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PingController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/ping")
    public Mono<ResponseEntity<Map<String, String>>> ping() {
        List<String> registeredServices = discoveryClient.getServices();
        
        List<String> requiredServices = Arrays.asList(
            "bakery-auth-service",
            "bakery-product-service",
            "bakery-cart-service",
            "bakery-order-service",
            "bakery-payment-service",
            "bakery-notification-service"
        );

        for (String service : requiredServices) {
            if (!registeredServices.contains(service)) {
                return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of(
                                "status", "ERROR",
                                "message", "services are down try later",
                                "missing_service", service
                        )));
            }
        }

        return Mono.just(ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "all ok"
        )));
    }
}
