package com.antra.iot.ioteventservice.service;

import com.antra.iot.ioteventservice.api.pojo.DeviceControlRequest;
import com.antra.iot.ioteventservice.api.pojo.DeviceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "iotDeviceService")
public interface DeviceServiceClient {
    @RequestMapping(method = RequestMethod.PUT, value="/device/status")
    ResponseEntity<DeviceVO> updateDeviceStatus(@RequestBody DeviceControlRequest request);
}
