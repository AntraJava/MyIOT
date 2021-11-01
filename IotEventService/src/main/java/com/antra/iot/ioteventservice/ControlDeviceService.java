package com.antra.iot.ioteventservice;

public interface ControlDeviceService {
    void sendControl(String homeId, String deviceId, String state);
}
