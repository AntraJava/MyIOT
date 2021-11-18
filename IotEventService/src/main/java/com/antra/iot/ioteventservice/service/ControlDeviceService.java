package com.antra.iot.ioteventservice.service;

import com.antra.iot.ioteventservice.api.pojo.DeviceControlRequest;

public interface ControlDeviceService {
    void sendControl(String homeId, String deviceId, String status);

    void sendUpdate(DeviceControlRequest deviceControlRequest);
}
