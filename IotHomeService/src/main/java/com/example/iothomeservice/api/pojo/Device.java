package com.example.iothomeservice.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private String id;
    private String type;
    private String name;
    private String serialNum;
    private String status;
}
