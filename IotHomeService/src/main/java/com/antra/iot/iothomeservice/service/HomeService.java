package com.antra.iot.iothomeservice.service;

import com.antra.iot.iothomeservice.api.pojo.Home;
import com.antra.iot.iothomeservice.api.pojo.NewHomeRequest;

import java.util.List;

public interface HomeService {
    Home createHome(NewHomeRequest newHomeRequest);

    List<Home> getHomeList(String userId);

    Home getHome(String hId);

    Home getHomeDetailById(String hId);
}
