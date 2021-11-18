package com.antra.iot.ioteventservice;

import com.antra.iot.ioteventservice.event.EventSender;
import com.antra.iot.ioteventservice.event.pojo.ControlDeviceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlDeviceServiceImpl implements ControlDeviceService {

    @Autowired
    EventSender sender;

    private static final String TOPIC_DEVICE_CONTROL = "device.control";

    @Override
    public void sendControl(String homeId, String deviceId, String status) {
        sender.sendMessageAsync(TOPIC_DEVICE_CONTROL+"."+homeId, new ControlDeviceMessage(homeId, deviceId,status));
    }
}
