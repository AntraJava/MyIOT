package com.antra.iot.iotdeviceservice.service;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceVO;
import com.antra.iot.iotdeviceservice.api.pojo.NewDeviceRequest;
import com.antra.iot.iotdeviceservice.entity.DeviceEntity;
import com.antra.iot.iotdeviceservice.repository.DeviceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
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
}
