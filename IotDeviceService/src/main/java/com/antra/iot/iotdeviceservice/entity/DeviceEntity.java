package com.antra.iot.iotdeviceservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "device")
@Getter
@Setter
public class DeviceEntity {
    @Id
    private String id;
    private String name;
    private String serialNum;
    private String homeId;
}
