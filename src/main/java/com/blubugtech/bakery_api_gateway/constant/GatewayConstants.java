package com.blubugtech.bakery_api_gateway.constant;

public final class GatewayConstants {
    private GatewayConstants() {}

    public static final String USER_ID_HEADER = "X-User-Id";
    public static final String USER_ROLE_HEADER = "X-User-Role";
    public static final String USER_EMAIL_HEADER = "X-User-Email";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String JWT_CLAIM_USER_ID = "userId";
    public static final String JWT_CLAIM_ROLE = "role";
    public static final String JWT_CLAIM_EMAIL = "email";
}
