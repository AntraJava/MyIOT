package com.antra.iot.iothomeservice.api.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Home {
    private String id;
    private String name;
    private String locationInfo;
    private String ownerId;
    private List<Device> devices;
}
