package com.antra.iot.iothomeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IotHomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotHomeServiceApplication.class, args);
    }

}
