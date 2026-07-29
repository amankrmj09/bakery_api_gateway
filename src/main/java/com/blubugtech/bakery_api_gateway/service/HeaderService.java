package com.blubugtech.bakery_api_gateway.service;

import com.blubugtech.bakery_api_gateway.model.AuthenticatedUser;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import com.blubugtech.bakery_api_gateway.constant.GatewayConstants;

@Service
public class HeaderService {

    public ServerHttpRequest addAuthenticatedHeaders(
            ServerHttpRequest request,
            AuthenticatedUser user
    ) {

        return request.mutate()
                .headers(headers -> {
                    headers.remove(GatewayConstants.USER_ID_HEADER);
                    headers.remove(GatewayConstants.USER_ROLE_HEADER);
                    headers.remove(GatewayConstants.USER_EMAIL_HEADER);
                })
                .header(GatewayConstants.USER_ID_HEADER, user.userId())
                .header(GatewayConstants.USER_ROLE_HEADER, user.role())
                .header(GatewayConstants.USER_EMAIL_HEADER, user.email() != null ? user.email() : "")
                .build();
    }
}
