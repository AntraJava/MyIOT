package com.antra.iot.ioteventservice.api.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class DeviceControlRequest {
    private String cmd;
    private String homeId;
    private String deviceId;
    private String status;
    private Map<String,String> data;
}
