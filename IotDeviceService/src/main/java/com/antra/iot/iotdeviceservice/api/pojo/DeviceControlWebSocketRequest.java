package com.antra.iot.iotdeviceservice.api.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class DeviceControlWebSocketRequest {
    private String cmd;
    private String homeId;
    private String deviceId;
    private String state;
    private Map<String,String> data;
}
