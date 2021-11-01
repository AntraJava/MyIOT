package com.antra.iot.ioteventservice.event.pojo;

public class ControlDeviceMessage {
    private String homeId;
    private String deviceId;
    private String state;

    public ControlDeviceMessage() {
    }

    public ControlDeviceMessage(String homeId, String deviceId, String state) {
        this.homeId = homeId;
        this.deviceId = deviceId;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
