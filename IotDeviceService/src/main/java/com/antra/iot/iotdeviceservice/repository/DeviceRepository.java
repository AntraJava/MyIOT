package com.antra.iot.iotdeviceservice.repository;

import com.antra.iot.iotdeviceservice.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {
    List<DeviceEntity> findAllByHomeId(String homeId);
}
