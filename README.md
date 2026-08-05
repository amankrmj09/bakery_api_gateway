# 🧁 API Gateway Service

![Java](https://img.shields.io/badge/Java-25-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.15-brightgreen.svg)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring%20Cloud%20Gateway-2025.0.3-blue.svg)

Welcome to the **API Gateway Service**, a core reactive component of the Shah's Bakery Microservice Platform.

## 📑 Table of Contents
- [Features](#-features)
- [Folder Structure](#-folder-structure)
- [Dependencies](#-dependencies)
- [API Reference](#-api-reference)
- [Endpoints](#-endpoints)
- [How to Run](#-how-to-run)
- [Related Links](#-related-links)

## ✨ Features
- **Centralized Routing & Load Balancing:** Directs requests to downstream microservices via Spring Cloud Netflix Eureka discovery (`lb://...`).
- **JWT Authentication Filtering:** Reactive gateway filter for validating tokens, verifying HMAC signatures, and enforcing public vs. protected route policies.
- **Security Header Propagation:** Strips untrusted user headers and injects validated `X-User-Id`, `X-User-Role`, and `X-User-Email` headers into downstream requests.
- **Hybrid Rate Limiting:** Redis-backed rate limiting using a key resolver based on authenticated User ID or fallback IP address.
- **Dynamic CORS Configuration:** Global WebFlux CORS filter configuration.
- **Service Availability Check:** Downstream service health verification endpoint (`/api/ping`).

## 📁 Folder Structure

```text
bakery_api_gateway/
├── .env                                       # Local environment variables
├── .env.example                               # Environment template example
├── .gitattributes                             # Git repository attributes
├── .gitignore                                 # Git ignore rules
├── Dockerfile                                 # Container build instructions
├── README.md                                  # Project documentation
├── API_REFERENCE.md                           # Detailed API & route reference
├── build.gradle.kts                           # Kotlin Gradle build script
├── gradle.properties                          # Gradle configuration properties
├── gradlew                                    # Unix Gradle wrapper script
├── gradlew.bat                                # Windows Gradle wrapper script
├── services_report.md                         # Architecture report
├── settings.gradle.kts                        # Gradle project settings
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar                 # Gradle wrapper JAR
│       └── gradle-wrapper.properties          # Gradle wrapper settings
└── src/
    ├── main/
    │   ├── java/com/blubugtech/bakery_api_gateway/
    │   │   ├── config/
    │   │   │   ├── CustomCorsConfig.java      # Dynamic CORS WebFlux configuration
    │   │   │   └── RateLimiterConfig.java     # Hybrid Redis rate-limiting (User ID / IP)
    │   │   ├── constant/
    │   │   │   └── GatewayConstants.java      # Header & JWT claim constants
    │   │   ├── controller/
    │   │   │   └── PingController.java        # Health check & downstream service verification
    │   │   ├── filter/
    │   │   │   ├── AuthenticationFilter.java  # Reactive Spring Cloud Gateway JWT filter
    │   │   │   └── LoggingFilter.java         # Global request/response logging filter
    │   │   ├── model/
    │   │   │   └── AuthenticatedUser.java     # User identity model record
    │   │   ├── service/
    │   │   │   ├── HeaderService.java         # Downstream request header injection
    │   │   │   ├── JwtService.java            # JWT parsing & validation logic
    │   │   │   └── PublicEndpointService.java # Public unauthenticated route definitions
    │   │   ├── util/
    │   │   │   └── JwtUtil.java               # Cryptographic token validation & claims extraction
    │   │   └── BakeryApiGatewayApplication.java # Application main entry point
    │   └── resources/
    │       ├── application.yml               # Base application configuration
    │       ├── application-dev.yml           # Development profile settings
    │       ├── application-docker.yml        # Docker environment configuration
    │       ├── application-prod.yml          # Production profile settings
    │       └── logback-spring.xml            # Logback logging layout configuration
    └── test/
        ├── java/com/blubugtech/bakery_api_gateway/
        │   └── BakeryApiGatewayApplicationTests.java # Context loading unit test
        ├── test_h1.http                       # End-to-end integration test suite 1
        ├── test_h2.http                       # End-to-end integration test suite 2
        ├── test_h3.http                       # End-to-end integration test suite 3
        └── test_h4.http                       # End-to-end integration test suite 4
```

## 🛠️ Dependencies
- **Framework:** Java 25, Spring Boot 3.5.15, Spring Cloud Gateway Server WebFlux (2025.0.3)
- **Service Discovery & Load Balancing:** Spring Cloud Netflix Eureka Client, Spring Cloud LoadBalancer
- **Security & Data:** JJWT 0.12.6, Spring Boot Data Redis Reactive
- **Documentation & Utilities:** Springdoc OpenAPI Starter WebFlux UI 2.8.4, Caffeine Cache, Lombok, Micrometer Prometheus

## 📖 API Reference
For full endpoint documentation, route mapping specs, public vs. protected route registries, header propagation details, and architecture flow, refer to [API_REFERENCE.md](API_REFERENCE.md).

## 🌐 Quick Endpoints Summary

- `GET /api/ping` — Health check verifying downstream service registration in Eureka Discovery.
- `POST /api/auth/login`, `POST /api/auth/register` — Public authentication routes forwarded to `bakery-auth-service`.
- `GET /api/products`, `GET /api/categories` — Public catalog routes forwarded to `bakery-product-service`.
- Protected routes (e.g. `/api/orders`, `/api/payments`) require `Authorization: Bearer <token>`.

> [!NOTE]
> OpenAPI documentation is available when running via Swagger UI at `http://localhost:8080/swagger-ui.html`.

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/amankrmj01/bakery_api_gateway.git
   cd bakery_api_gateway
   ```

2. **Configure Environment:**
   Ensure environment variables (`JWT_SECRET`, `EUREKA_URL`, etc.) or `.env` are properly set.

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

## 🔗 Related Links
- [Parent Repository](https://github.com/amankrmj09/Blu_s_Bakery)
- [API Reference](./API_REFERENCE.md)
- [API Reference Manual](API_REFERENCE.md)
- [Main Platform README](../README.md)
