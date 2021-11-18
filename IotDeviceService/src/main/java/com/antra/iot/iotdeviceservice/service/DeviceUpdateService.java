package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;

public interface DeviceUpdateService {
    DeviceVO updateDevice(DeviceControlRequest request);
}
