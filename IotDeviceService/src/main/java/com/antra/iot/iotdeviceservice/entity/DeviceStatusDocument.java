package com.antra.iot.iotdeviceservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("device_status")
@Data
public class DeviceStatusDocument {
    private String id;
    private String deviceId;
    private String homeId;
    private String status;
    private Long timestamp;
    private String createdBy;
}
