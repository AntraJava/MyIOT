package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.api.pojo.NewDeviceRequest;
import com.antra.iot.iotdeviceservice.service.DeviceService;
import com.antra.iot.iotdeviceservice.service.DeviceUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceUpdateService deviceUpdateService;

    public DeviceController(DeviceService deviceService, DeviceUpdateService deviceUpdateService) {
        this.deviceService = deviceService;
        this.deviceUpdateService = deviceUpdateService;
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

    @PutMapping("/status")
    ResponseEntity<DeviceVO> updateDeviceStatus(@RequestBody DeviceControlRequest request){
        log.info("Got request to update status of {}", request.getDeviceId());
        return ResponseEntity.ok(this.deviceUpdateService.updateDevice(request));
    }
}
