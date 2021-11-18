package com.antra.iot.ioteventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IotEventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotEventServiceApplication.class, args);
    }

}
