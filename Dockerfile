FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the jar file
COPY build/libs/bakery_api_gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
