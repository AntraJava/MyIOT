package com.antra.iot.iotdeviceservice.service.feign;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value= "iotEventService")
public interface EventServiceClient {
    @RequestMapping(method = RequestMethod.POST, value="/event/control")
    ResponseEntity<DeviceVO> controlDevice(@RequestBody DeviceControlRequest request);
}
