package com.antra.iot.ioteventservice.service;

import com.antra.iot.ioteventservice.api.pojo.DeviceControlRequest;
import com.antra.iot.ioteventservice.event.EventSender;
import com.antra.iot.ioteventservice.event.pojo.ControlDeviceMessage;
import org.springframework.stereotype.Service;

@Service
public class ControlDeviceServiceImpl implements ControlDeviceService {

    private final EventSender sender;
    private final DeviceServiceClient deviceServiceClient;

    public ControlDeviceServiceImpl(EventSender sender, DeviceServiceClient deviceServiceClient) {
        this.sender = sender;
        this.deviceServiceClient = deviceServiceClient;
    }

    private static final String TOPIC_DEVICE_CONTROL = "device.control";

    @Override
    public void sendControl(String homeId, String deviceId, String status) {
        sender.sendMessageAsync(TOPIC_DEVICE_CONTROL+"."+homeId, new ControlDeviceMessage(homeId, deviceId,status));
    }

    @Override
    public void sendUpdate(DeviceControlRequest request) {
        deviceServiceClient.updateDeviceStatus(request);
    }
}
