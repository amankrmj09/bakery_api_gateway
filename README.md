# Bakery API Gateway

## Overview
The API Gateway serves as the single entry point for all client applications, routing requests to appropriate backend microservices with integrated security, rate limiting, and service discovery.

## Features
- Dynamic service discovery with Eureka  
- Load balancing across service instances  
- Request logging and security filtering  
- CORS support for cross-origin requests  
- Health monitoring and metrics exposition  

## Dependencies
- Spring Cloud Gateway  
- Spring Cloud Netflix Eureka Client  
- Spring Boot Actuator  
- Spring Security  

## Key Endpoints
- `/api/auth/**` → Auth Service  
- `/api/orders/**` → Order Service  
- `/api/cart/**` → Cart Service  
- `/api/payments/**` → Payment Service  
- `/api/products/**` → Product Service  
- `/api/notifications/**` → Notification Service  

## Running
```
./gradlew bootRun
```

Runs on port 8080 by default.

## Documentation
Swagger UI available at:  
`http://localhost:8080/swagger-ui.html`
