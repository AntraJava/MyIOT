package com.antra.iot.ioteventservice.api;

import com.antra.iot.ioteventservice.api.pojo.DeviceControlRequest;
import com.antra.iot.ioteventservice.api.pojo.DeviceVO;
import com.antra.iot.ioteventservice.service.ControlDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EventController {

    @Autowired
    ControlDeviceService controlDeviceService;

    @RequestMapping(method = RequestMethod.POST, value="/event/control")
    public ResponseEntity<DeviceVO> controlDevice(@RequestBody DeviceControlRequest request){
        controlDeviceService.sendControl(request.getHomeId(), request.getDeviceId(), request.getStatus());
        DeviceVO vo = new DeviceVO();
        return ResponseEntity.ok(vo);
    }
}
