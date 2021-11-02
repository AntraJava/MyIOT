package com.example.iothomeservice.service;

import com.example.iothomeservice.api.pojo.Home;
import com.example.iothomeservice.api.pojo.NewHomeRequest;
import com.example.iothomeservice.entity.HomeEntity;
import com.example.iothomeservice.repository.HomeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeRepository homeRepository;

    /**
     * Why do we three classes, NewHomeRequest, Home, HomeEntity with similar fields ?
     * For future extensibility, we may have different fields for http request, database column and response json fields.
     * Even now they are almost identical.
     * @param newHomeRequest
     * @return home object
     */
    @Override
    public Home createHome(NewHomeRequest newHomeRequest) {
        HomeEntity homeToBeSaved = new HomeEntity();
        BeanUtils.copyProperties(newHomeRequest, homeToBeSaved);
        homeToBeSaved.setId(UUID.randomUUID().toString());
        homeToBeSaved = homeRepository.save(homeToBeSaved);
        Home home = new Home();
        BeanUtils.copyProperties(homeToBeSaved, home);
        return home;
    }
}
