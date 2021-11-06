package com.antra.iot.authservice.service.feign;

import com.antra.iot.authservice.jwt.JwtRequest;
import com.antra.iot.authservice.pojo.Customer;
import com.antra.iot.authservice.pojo.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "iotUserService")
public interface CustomerServiceClient {
    @RequestMapping(method = RequestMethod.POST, value="/customer/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseMessage<Customer>> getCustomer(@RequestBody JwtRequest request);

}
