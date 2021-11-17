package com.antra.iot.iotdeviceservice.repository;

import com.antra.iot.iotdeviceservice.entity.DeviceStatusDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceStatusRepository extends MongoRepository<DeviceStatusDocument, String> {
    List<DeviceStatusDocument> findByHomeId(String homeId);

    DeviceStatusDocument findFirstByDeviceIdOrderByTimestampDesc(String deviceId);
}
