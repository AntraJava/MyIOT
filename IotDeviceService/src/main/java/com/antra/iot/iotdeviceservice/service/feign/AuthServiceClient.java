package com.antra.iot.iotdeviceservice.service.feign;

import com.antra.iot.iotdeviceservice.api.pojo.Customer;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value= "authService")
public interface AuthServiceClient {
    @RequestMapping(method = RequestMethod.POST, value="/validate")
    ResponseEntity<Customer> verifyToken(@RequestBody Map<String,String> token);
}
