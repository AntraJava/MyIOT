package com.antra.iot.iotdeviceservice.service.feign;

import com.antra.iot.iotdeviceservice.api.pojo.Home;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value= "iotHomeService")
public interface HomeServiceClient {
    @RequestMapping(method = RequestMethod.GET, value="/home")
    ResponseEntity<Home> getHomeById(@RequestParam String homeId);
}
