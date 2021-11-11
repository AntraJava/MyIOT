package com.antra.iot.iotdeviceservice.api.pojo;

import lombok.Data;

@Data
public class NewDeviceRequest {
    private String serialNum;
    private String name;
    private String homeId;
}
