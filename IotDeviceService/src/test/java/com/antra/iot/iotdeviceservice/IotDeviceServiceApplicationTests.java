package com.antra.iot.iotdeviceservice;

import com.antra.iot.iotdeviceservice.repository.DeviceStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IotDeviceServiceApplicationTests {

    @Autowired
    DeviceStatusRepository statusRepository;

    @Test
    void setStatusRepository() {
        System.out.println(statusRepository.findFirstByDeviceIdOrderByTimestampDesc("c1c73bc0-ef37-4297-87a7-dfd2ff423af9"));
    }

}
