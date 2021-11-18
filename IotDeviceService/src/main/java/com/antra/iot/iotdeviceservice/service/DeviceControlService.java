package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlWebSocketRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;

import java.util.List;

public interface DeviceControlService {
    List<DeviceVO> processControl(DeviceControlWebSocketRequest message);
}
