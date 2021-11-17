package com.antra.iot.iotdeviceservice.service.feign;

import com.antra.iot.iotdeviceservice.api.pojo.Customer;
import com.antra.iot.iotdeviceservice.api.pojo.JwtToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "authService")
public interface AuthServiceClient {
    @RequestMapping(method = RequestMethod.POST, value="/validate")
    ResponseEntity<Customer> verifyToken(@RequestBody JwtToken token);
}
