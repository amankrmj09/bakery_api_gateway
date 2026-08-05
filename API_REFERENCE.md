# API Reference - Bakery API Gateway

This document contains the API reference for the API Gateway service.

## Ping Controller

**Source File**: [`PingController`](./src/main/java/com/blubugtech/bakery_api_gateway/controller/PingController.java)

### GET `/api/ping`

Checks if all required microservices are registered and available.

#### Request Body
No request body is required for this endpoint.
```json
null
```

#### Response Body (200 OK)
Returned when all required microservices are successfully registered and running.
```json
{
  "status": "OK",
  "message": "all ok"
}
```

#### Response Body (503 Service Unavailable)
Returned when one or more required microservices are not registered.
```json
{
  "status": "ERROR",
  "message": "services are down try later",
  "missing_service": "bakery-auth-service"
}
```
