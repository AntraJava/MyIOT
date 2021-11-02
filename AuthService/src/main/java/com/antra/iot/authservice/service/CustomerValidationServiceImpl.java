package com.antra.iot.authservice.service;

import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.pojo.Customer;
import com.antra.iot.authservice.service.feign.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {
    @Autowired
    CustomerServiceClient customerServiceClient;


    @Override
    public Customer verifyLogin(JwtRequest authenticationRequest) {
        return customerServiceClient.loadQuestionsByAssessment(authenticationRequest).getBody();
    }
}
