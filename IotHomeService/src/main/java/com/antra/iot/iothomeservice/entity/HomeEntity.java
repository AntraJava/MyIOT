package com.antra.iot.iothomeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "home")
@Getter
@Setter
public class HomeEntity {
    @Id
    private String id;
    private String name;
    private String locationInfo;
    private String ownerId;
}
