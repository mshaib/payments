package com.intuit.b2b;

import com.intuit.b2b.healthCheck.HealthCheckController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PaymentsApplicationTests {

    @Autowired
    private HealthCheckController healthCheckController;

    @Test
    void contextLoads() {
        assertThat(healthCheckController).isNotNull();
    }

}
