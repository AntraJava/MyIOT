package com.antra.iot.iotdeviceservice.api.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Home {
    private String id;
    private String name;
    private String locationInfo;
    private String ownerId;
}
