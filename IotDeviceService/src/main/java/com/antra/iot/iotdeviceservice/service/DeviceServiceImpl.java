package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.api.pojo.NewDeviceRequest;
import com.antra.iot.iotdeviceservice.entity.DeviceEntity;
import com.antra.iot.iotdeviceservice.entity.DeviceStatusDocument;
import com.antra.iot.iotdeviceservice.repository.DeviceRepository;
import com.antra.iot.iotdeviceservice.repository.DeviceStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceStatusRepository deviceStatusRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceStatusRepository deviceStatusRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceStatusRepository = deviceStatusRepository;
    }

    @Override
    public DeviceVO addDeviceToHome(NewDeviceRequest request) {
        // check serial number. it should be there when device is produced.
        // if(checkSerialNumber(request.getSerialNum())){
        // for simplicity reason, we didn't implement this
        // };
        DeviceEntity entity = new DeviceEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setId(UUID.randomUUID().toString());
        DeviceEntity savedEntity = deviceRepository.save(entity);
        DeviceVO vo = new DeviceVO();
        BeanUtils.copyProperties(savedEntity, vo);
        //status
        DeviceStatusDocument status = new DeviceStatusDocument();
        status.setStatus("off");
        status.setDeviceId(entity.getId());
        status.setHomeId(entity.getHomeId());
        status.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        status.setCreatedBy("Web");
        deviceStatusRepository.save(status);
        return vo;
    }

    @Override
    public List<DeviceVO> getDevicesByHomeId(String homeId) {
        DeviceEntity d = new DeviceEntity();
        d.setHomeId(homeId);
        Example<DeviceEntity> example = Example.of(d);
        List<DeviceEntity> devicesEntities = deviceRepository.findAll(example);
        return devicesEntities.stream().map(entity -> {
            DeviceVO vo = new DeviceVO();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DeviceVO> getDevicesStatusByHomeId(String homeId) {
        List<DeviceEntity> devices = deviceRepository.findAllByHomeId(homeId);
        return devices.stream().filter(Objects::nonNull).map(entity -> {
            DeviceStatusDocument status = deviceStatusRepository.findFirstByDeviceIdOrderByTimestampDesc(entity.getId());
            DeviceVO vo = new DeviceVO();
            vo.setStatus(status.getStatus());
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
