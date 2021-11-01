package com.example.iothomeservice.api.pojo;

import lombok.Data;

import java.util.List;

@Data
public class HomeConfig {
    private String id;
    private String name;
    private List<Device> devices;
}
