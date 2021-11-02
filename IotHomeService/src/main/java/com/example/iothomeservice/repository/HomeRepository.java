package com.example.iothomeservice.repository;


import com.example.iothomeservice.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<HomeEntity, String> {

}
