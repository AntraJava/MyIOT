package com.antra.iot.ioteventservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IotEventServiceApplicationTests {

    @Autowired
    ControlDeviceService service;

    @Test
    void contextLoads() {
//        sender.sendMessage("device.state",Apple.builder().color("RED").id("123").build());
        service.sendControl("123","a1","on");
        service.sendControl("123","a2","on");
        service.sendControl("123","a3","on");
//        service.sendControl("123","a41","off");
    }

}
