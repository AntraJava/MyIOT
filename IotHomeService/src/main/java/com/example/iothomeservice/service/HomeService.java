package com.example.iothomeservice.service;

import com.example.iothomeservice.api.pojo.Home;
import com.example.iothomeservice.api.pojo.NewHomeRequest;

public interface HomeService {
    Home createHome(NewHomeRequest newHomeRequest);
}
