package com.example.iothomeservice.service;

import com.example.iothomeservice.api.pojo.Home;
import com.example.iothomeservice.api.pojo.NewHomeRequest;

import java.util.List;

public interface HomeService {
    Home createHome(NewHomeRequest newHomeRequest);

    List<Home> getHomeList(String userId);
}
