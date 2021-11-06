package com.antra.iot.iotuserservice.vo;

import com.antra.iot.iotuserservice.entity.Customer;

public class CustomerVO {
    Customer customer;
    public CustomerVO(Customer customer){
        this.customer = customer;
    }
    public String getId(){
        return customer.getId();
    }
    public String getName() {
        return customer.getName();
    }
    public String getUsername() {
        return customer.getUsername();
    }
    public String getEmail() {
        return customer.getEmail();
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "customer=" + customer +
                '}';
    }
}
