package com.antra.iot.ioteventservice.event;

import com.antra.iot.ioteventservice.config.KafkaClientConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    @KafkaListener(topicPattern = "device.*", groupId = KafkaClientConfig.GROUP_ID)
    public void listenGroupFoo(Object message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
