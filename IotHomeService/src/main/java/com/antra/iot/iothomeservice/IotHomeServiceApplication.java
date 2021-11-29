package com.antra.iot.iothomeservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Home Service API", version = "1.0", description = "Documentation IotHomeService API v1.0")
)
public class IotHomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotHomeServiceApplication.class, args);
    }

}
