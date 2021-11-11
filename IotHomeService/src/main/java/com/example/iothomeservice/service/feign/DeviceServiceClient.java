package com.example.iothomeservice.service.feign;

import com.example.iothomeservice.api.pojo.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value= "iotDeviceService")
public interface DeviceServiceClient {
    @RequestMapping(method = RequestMethod.GET, value="/device")
    ResponseEntity<List<Device>> getDevicesByHomeId(@RequestParam String homeId);
}
