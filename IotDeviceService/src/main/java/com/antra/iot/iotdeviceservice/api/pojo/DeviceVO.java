package com.antra.iot.iotdeviceservice.api.pojo;

import lombok.Data;

@Data
public class DeviceVO {
    private String id;
    private String name;
    private String serialNum;
    private String homeId;
    private String status;
}
