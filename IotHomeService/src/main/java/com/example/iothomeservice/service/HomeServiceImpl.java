package com.example.iothomeservice.service;

import com.example.iothomeservice.api.pojo.Home;
import com.example.iothomeservice.api.pojo.NewHomeRequest;
import com.example.iothomeservice.entity.HomeEntity;
import com.example.iothomeservice.repository.HomeRepository;
import com.example.iothomeservice.service.feign.DeviceServiceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    DeviceServiceClient deviceServiceClient;

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

    @Override
    public List<Home> getHomeList(String userId) {
        HomeEntity home = new HomeEntity();
        home.setOwnerId(userId);
        Example<HomeEntity> example = Example.of(home);
        List<Home> homeList = homeRepository.findAll(example).stream().map(entity->{
            Home h  = new Home();
            BeanUtils.copyProperties(entity, h);
            return h;
        }).collect(Collectors.toList());
        for (Home h : homeList){
            h.setDevices(deviceServiceClient.getDevicesByHomeId(h.getId()).getBody());
        }
        return homeList;
    }

    @Override
    public Home getHome(String hId) {
        return homeRepository.findById(hId).map(entity -> {
            Home h = new Home();
            h.setId(entity.getId());
            h.setName(entity.getName());
            h.setOwnerId(entity.getOwnerId());
            h.setLocationInfo(entity.getLocationInfo());
            return h;
        }).orElseGet(null);
    }
}
