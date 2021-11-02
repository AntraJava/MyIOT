package com.antra.iot.iotuserservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;

}
