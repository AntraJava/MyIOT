package com.example.iothomeservice.api.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewHomeRequest {
    @NotBlank
    private String name;
    private String locationInfo;
    @NotBlank
    private String ownerId;
}