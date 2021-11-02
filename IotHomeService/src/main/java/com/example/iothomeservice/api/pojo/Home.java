package com.example.iothomeservice.api.pojo;

import lombok.Data;

@Data
public class Home {
    private String id;
    private String name;
    private String locationInfo;
    private String ownerId;
}
