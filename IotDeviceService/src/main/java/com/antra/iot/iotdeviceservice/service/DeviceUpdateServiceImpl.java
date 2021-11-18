package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlRequest;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.entity.DeviceStatusDocument;
import com.antra.iot.iotdeviceservice.repository.DeviceStatusRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceUpdateServiceImpl implements DeviceUpdateService {

    private final DeviceStatusRepository deviceStatusRepository;
    private final SimpMessagingTemplate messageTemplate;

    public DeviceUpdateServiceImpl(DeviceStatusRepository deviceStatusRepository, SimpMessagingTemplate messageTemplate) {
        this.deviceStatusRepository = deviceStatusRepository;
        this.messageTemplate = messageTemplate;
    }

    @Override
    public DeviceVO updateDevice(DeviceControlRequest request) {
        DeviceStatusDocument status = new DeviceStatusDocument();
        status.setStatus(request.getStatus());
        status.setDeviceId(request.getDeviceId());
        status.setHomeId(request.getHomeId());
        status.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        deviceStatusRepository.save(status);

        List<DeviceVO> devicesStatusList = new ArrayList<>();
        DeviceVO vo = new DeviceVO();
        vo.setId(request.getDeviceId());
        vo.setHomeId(request.getHomeId());
        vo.setStatus(request.getStatus());
        devicesStatusList.add(vo);
        this.messageTemplate.convertAndSend("/queue/home/" + request.getHomeId(), devicesStatusList);
        return vo;
    }
}
