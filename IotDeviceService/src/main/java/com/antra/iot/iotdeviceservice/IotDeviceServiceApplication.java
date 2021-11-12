package com.antra.iot.iotdeviceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IotDeviceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotDeviceServiceApplication.class, args);
    }

}
