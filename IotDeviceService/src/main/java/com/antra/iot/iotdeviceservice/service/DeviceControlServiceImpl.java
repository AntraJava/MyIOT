package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlWebSocketRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.entity.DeviceStatusDocument;
import com.antra.iot.iotdeviceservice.repository.DeviceStatusRepository;
import com.antra.iot.iotdeviceservice.service.feign.EventServiceClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class DeviceControlServiceImpl implements DeviceControlService {

    private DeviceStatusRepository deviceStatusRepository;
    private EventServiceClient eventServiceClient;

    public DeviceControlServiceImpl(DeviceStatusRepository deviceStatusRepository, EventServiceClient eventServiceClient) {
        this.deviceStatusRepository = deviceStatusRepository;
        this.eventServiceClient = eventServiceClient;
    }

    @Override
    public List<DeviceVO> processControl(DeviceControlWebSocketRequest message) {
// create a DB record
        DeviceStatusDocument status = new DeviceStatusDocument();
        status.setStatus(message.getStatus());
        status.setDeviceId(message.getDeviceId());
        status.setHomeId(message.getHomeId());
        status.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        status.setCreatedBy("Web");
        deviceStatusRepository.save(status);
// notify event service to send msg to device
        DeviceControlRequest controlRequest = new DeviceControlRequest();
        controlRequest.setStatus(message.getStatus());
        controlRequest.setDeviceId(message.getDeviceId());
        controlRequest.setHomeId(message.getHomeId());
        eventServiceClient.controlDevice(controlRequest);
        // wait for the signal

        DeviceVO result = new DeviceVO();
        result.setId(message.getDeviceId());
        result.setHomeId(message.getHomeId());
        result.setStatus(message.getStatus());

        return List.of(result);
    }
}
