package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.api.pojo.NewDeviceRequest;
import com.antra.iot.iotdeviceservice.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceVO> addNewDevice(@RequestBody NewDeviceRequest request, @RequestHeader("userId") String customerId) {
        DeviceVO addedDevice = deviceService.addDeviceToHome(request);
        return ResponseEntity.ok(addedDevice);
    }

    @GetMapping
    public ResponseEntity<List<DeviceVO>> getDevicesByHomeId(@RequestParam String homeId) {
        List<DeviceVO> devices = deviceService.getDevicesByHomeId(homeId);
        return ResponseEntity.ok(devices);
    }
}
