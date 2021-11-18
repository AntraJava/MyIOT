package com.antra.iot.iothomeservice.api.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewHomeRequest {
    @NotBlank
    private String name;
    private String locationInfo;
    private String ownerId;
}
