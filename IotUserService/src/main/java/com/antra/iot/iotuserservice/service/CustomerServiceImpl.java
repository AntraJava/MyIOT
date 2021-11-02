package com.antra.iot.iotuserservice.service;

import com.antra.iot.iotuserservice.api.pojo.RegisterRequest;
import com.antra.iot.iotuserservice.entity.Customer;
import com.antra.iot.iotuserservice.exception.CustomerException;
import com.antra.iot.iotuserservice.repository.CustomerRepository;
import com.antra.iot.iotuserservice.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerVO saveCustomer(RegisterRequest customerRequest) {
        if(customerRepository.existsByUsername(customerRequest.getUsername())) {
            throw new CustomerException("Username is already taken!");
        }
        if(customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new CustomerException("Email Address already in use!");
        }
        Customer c = new Customer();
        c.setId(UUID.randomUUID().toString());
        c.setEmail(customerRequest.getEmail());
        c.setName(customerRequest.getName());
        c.setUsername(customerRequest.getUsername());
        c.setPassword(new BCryptPasswordEncoder(13).encode(customerRequest.getPassword()));
        Customer savedCustomer = customerRepository.save(c);
        return new CustomerVO(savedCustomer);
    }
}
