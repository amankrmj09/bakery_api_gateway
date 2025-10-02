package com.shah_s.bakery_api_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "eureka.client.enabled=false"
})
class BakeryApiGatewayApplicationTests {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
    }
}
