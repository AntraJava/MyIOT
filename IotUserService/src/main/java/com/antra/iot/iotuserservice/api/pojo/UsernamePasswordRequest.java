package com.antra.iot.iotuserservice.api.pojo;

import lombok.Data;

@Data
public class UsernamePasswordRequest {
    private String username;
    private String password;
}
