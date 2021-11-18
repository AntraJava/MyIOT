package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.service.DeviceControlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/control")
public class DeviceControlController {

    private DeviceControlService deviceControlService;

    public DeviceControlController(DeviceControlService deviceControlService) {
        this.deviceControlService = deviceControlService;
    }

//    @PostMapping
//    public ResponseEntity<DeviceVO> addNewDevice(@RequestBody NewDeviceRequest request, @RequestHeader("userId") String customerId) {
//        List<DeviceVO> result = deviceControlService.processControl(message);
//
//        return ResponseEntity.ok(addedDevice);
//    }
}
