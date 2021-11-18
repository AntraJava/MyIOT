package com.antra.iot.iothomeservice.repository;


import com.antra.iot.iothomeservice.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<HomeEntity, String> {

}
