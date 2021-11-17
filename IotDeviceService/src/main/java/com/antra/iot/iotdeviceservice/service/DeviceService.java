package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.api.pojo.NewDeviceRequest;

import java.util.List;

public interface DeviceService {
    DeviceVO addDeviceToHome(NewDeviceRequest request);

    List<DeviceVO> getDevicesByHomeId(String homeId);

    List<DeviceVO> getDevicesStatusByHomeId(String homeId);
}
