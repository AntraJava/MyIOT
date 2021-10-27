package com.antra.iot.iotuserservice.vo;

import com.antra.iot.iotuserservice.entity.Customer;

public class CustomerVO {
    Customer customer;
    public CustomerVO(Customer customer){
        this.customer = customer;
    }
    public Long getId(){
        return customer.getId();
    }
    public String getName() {
        return customer.getName();
    }
    public String getEmail() {
        return customer.getEmail();
    }
}
