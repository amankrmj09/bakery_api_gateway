package com.blubugtech.bakery_api_gateway.service;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class HeaderService {

    private static final String USER_ID = "X-User-Id";
    private static final String USER_ROLE = "X-User-Role";
    private static final String USER_EMAIL = "X-User-Email";

    public ServerHttpRequest addAuthenticatedHeaders(
            ServerHttpRequest request,
            AuthenticatedUser user
    ) {

        return request.mutate()
                .headers(headers -> {
                    headers.remove(USER_ID);
                    headers.remove(USER_ROLE);
                    headers.remove(USER_EMAIL);
                })
                .header(USER_ID, user.userId())
                .header(USER_ROLE, user.role())
                .header(USER_EMAIL, user.email() != null ? user.email() : "")
                .build();
    }
}
