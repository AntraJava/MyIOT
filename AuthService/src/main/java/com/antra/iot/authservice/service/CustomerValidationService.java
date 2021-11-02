package com.antra.iot.authservice.service;


import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.pojo.Customer;

public interface CustomerValidationService {
    Customer verifyLogin(JwtRequest authenticationRequest);
}
