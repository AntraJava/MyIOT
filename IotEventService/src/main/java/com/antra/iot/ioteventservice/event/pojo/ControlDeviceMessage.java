package com.antra.iot.ioteventservice.event.pojo;

public class ControlDeviceMessage {
    private String homeId;
    private String deviceId;
    private String status;

    public ControlDeviceMessage() {
    }

    public ControlDeviceMessage(String homeId, String deviceId, String status) {
        this.homeId = homeId;
        this.deviceId = deviceId;
        this.status = status;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
