package com.antra.iot.ioteventservice;

import com.antra.iot.ioteventservice.event.EventSender;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IotEventServiceApplicationTests {

    @Autowired
    EventSender sender;

    @Test
    void contextLoads() {
        sender.sendMessage("device.status",Apple.builder().color("RED").id("123").build());
        sender.sendMessageAsync("device.heartbeat",Apple.builder().color("Blue").id("aaa").build());
    }

}

@Data
@Builder
class Apple{
    private String color;
    private String id;
}