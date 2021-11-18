package com.antra.iot.ioteventservice.event;

import com.antra.iot.ioteventservice.api.pojo.DeviceControlRequest;
import com.antra.iot.ioteventservice.config.KafkaClientConfig;
import com.antra.iot.ioteventservice.service.ControlDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    private final ControlDeviceService controlDeviceService;

    public EventListener(ControlDeviceService controlDeviceService) {
        this.controlDeviceService = controlDeviceService;
    }

    @KafkaListener(topicPattern = "device.*", groupId = KafkaClientConfig.GROUP_ID)
    public void listenGroupFoo(DeviceControlRequest message) {
        log.info("Received Message in group foo: " + message);
        // need to check homeid/deviceId/user token etc. omit for now
        controlDeviceService.sendUpdate(message);
    }
}
