package com.blubugtech.bakery_api_gateway.model;

public record AuthenticatedUser(
        String userId,
        String role
) {}
