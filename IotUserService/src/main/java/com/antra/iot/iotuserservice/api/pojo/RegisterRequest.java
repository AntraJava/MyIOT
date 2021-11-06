package com.antra.iot.iotuserservice.api.pojo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 4, max = 150)
    private String name;

    @NotBlank
    @Size(min = 3, max = 150)
    private String username;

    @NotBlank
    @Size(max = 150)
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 50)
    private String password;

    @NotNull
    private Boolean admin = false;
}