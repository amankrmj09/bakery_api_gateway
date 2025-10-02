# Bakery API Gateway

Central entry point for all bakery microservices. Routes requests and provides cross-cutting concerns.

## Features

- **Service Discovery**: Auto-discovers services via Eureka
- **Load Balancing**: Distributes requests across service instances
- **CORS Support**: Handles cross-origin requests for web clients
- **Request Logging**: Logs all gateway traffic
- **Health Monitoring**: Integrated health checks and metrics

## Quick Start

### Run Locally
./gradlew bootRun


### Prerequisites
- Eureka Server running on port 8761

## API Routes

| Path | Target Service | Purpose |
|------|---------------|---------|
| `/api/auth/**` | auth-service | Authentication |
| `/api/orders/**` | order-service | Order management |
| `/api/products/**` | inventory-service | Product catalog |
| `/api/payments/**` | payment-service | Payment processing |
| `/api/notifications/**` | notification-service | Notifications |

## Endpoints

- **Gateway Routes**: http://localhost:8080/actuator/gateway/routes
- **Health Check**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/prometheus
