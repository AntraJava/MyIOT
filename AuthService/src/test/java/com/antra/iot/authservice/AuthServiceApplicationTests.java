package com.antra.iot.authservice;

import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.service.feign.CustomerServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceApplicationTests {
    @Autowired
    CustomerServiceClient customerServiceClient;
    @Test
    void contextLoads() {
        JwtRequest request = new JwtRequest();
        request.setUsername("zdwrz");
        request.setPassword("1231231");
        customerServiceClient.getCustomer(request);
    }

}
