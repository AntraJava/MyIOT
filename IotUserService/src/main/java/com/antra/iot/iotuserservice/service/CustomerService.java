package com.antra.iot.iotuserservice.service;

import com.antra.iot.iotuserservice.api.pojo.RegisterRequest;
import com.antra.iot.iotuserservice.api.pojo.UsernamePasswordRequest;
import com.antra.iot.iotuserservice.vo.CustomerVO;

public interface CustomerService {
    CustomerVO saveCustomer(RegisterRequest customer);

    CustomerVO verifyCustomer(UsernamePasswordRequest request);
}
