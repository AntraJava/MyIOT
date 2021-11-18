package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlWebSocketRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceControlServiceImpl implements DeviceControlService {

    @Override
    public List<DeviceVO> processControl(DeviceControlWebSocketRequest message) {
        DeviceVO test = new DeviceVO();
        test.setId(message.getDeviceId());
        test.setHomeId(message.getHomeId());
        test.setStatus(message.getStatus());
        return List.of(test);
    }
}
