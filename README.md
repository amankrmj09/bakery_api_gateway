# 🧁 API Gateway Service

![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)

Welcome to the **API Gateway Service**, a core component of the Shah's Bakery Microservice Platform.

## 📑 Table of Contents
- [Features](#-features)
- [Folder Structure](#-folder-structure)
- [Dependencies](#-dependencies)
- [Endpoints](#-endpoints)
- [How to Run](#-how-to-run)
- [Related Links](#-related-links)

## ✨ Features
- Centralized request routing and load balancing.
- JWT-based authentication and authorization filtering.
- Distributed Redis-backed rate limiting.

## 📁 Folder Structure
The main `src/main/java` directory is organized as follows:
```text
src/
└── main/
    └── java/.../bakery_api_gateway/
        ├── config/     # Custom configurations for CORS and Redis Rate Limiting.
        ├── controller/ # Ping and health check endpoints.
        ├── filter/     # Gateway filters, including JWT authentication and request logging.
        └── util/       # Utility classes, specifically for JWT parsing and validation.
```

## 🛠️ Dependencies
- **Framework:** Spring Boot, Spring Cloud Gateway
- **Key Modules:** Eureka Client, Spring Boot Actuator

## 🌐 Endpoints
> [!NOTE]
> For complete and detailed API definitions, please refer to the OpenAPI Reference available via the API Gateway's Swagger UI (`http://localhost:8080/swagger-ui.html`).

- `GET /api/**` - Routes requests to appropriate downstream microservices.
- `POST /api/**` - Routes requests to appropriate downstream microservices.

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/amankrmj01/bakery_api_gateway.git
   cd bakery_api_gateway
   ```

2. **Configure Environment:**
   Ensure your `.env` or `application.yml` properties are configured correctly.

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

## 🔗 Related Links
- [Main Platform README](../README.md)
