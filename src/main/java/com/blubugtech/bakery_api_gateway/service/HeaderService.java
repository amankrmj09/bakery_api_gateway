package com.blubugtech.bakery_api_gateway.service;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class HeaderService {

    private static final String USER_ID = "X-User-Id";
    private static final String USER_ROLE = "X-User-Role";

    public ServerHttpRequest addAuthenticatedHeaders(
            ServerHttpRequest request,
            AuthenticatedUser user
    ) {

        return request.mutate()
                .headers(headers -> {
                    headers.remove(USER_ID);
                    headers.remove(USER_ROLE);
                })
                .header(USER_ID, user.userId())
                .header(USER_ROLE, user.role())
                .build();
    }
}
