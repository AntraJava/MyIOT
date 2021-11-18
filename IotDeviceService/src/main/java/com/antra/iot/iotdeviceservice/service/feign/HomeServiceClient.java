package com.antra.iot.iotdeviceservice.service.feign;

import com.antra.iot.iotdeviceservice.api.pojo.Home;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "iotHomeService")
public interface HomeServiceClient {
    @RequestMapping(method = RequestMethod.GET, value="/home/{id}")
    ResponseEntity<Home> getHomeById(@PathVariable String id);
}
