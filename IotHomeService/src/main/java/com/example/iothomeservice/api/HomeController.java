package com.example.iothomeservice.api;

import com.example.iothomeservice.api.pojo.*;
import com.example.iothomeservice.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/config/{hId}")
    public ResponseEntity<HomeConfig> getHomeConfig(@PathVariable String hId) {
        HomeConfig testConfig = new HomeConfig();
        testConfig.setId("a1b2");
        testConfig.setName("Test Home");
        testConfig.setDevices(List.of(new Device("a1","switch","LighA", Constants.SWITCH_STATE_ON),
                new Device("a2","switch","LighB", Constants.SWITCH_STATE_OFF),
                new Device("a3","switch","LighC", Constants.SWITCH_STATE_ON), 
                new Device("a4","switch","LighD", Constants.SWITCH_STATE_OFF)));
        return ResponseEntity.ok(testConfig);
    }

    @PostMapping
    public ResponseEntity<Home> createNewHome(@Validated @RequestBody NewHomeRequest newHomeRequest, @RequestHeader("userId")String userId) {
        newHomeRequest.setOwnerId(userId);
        Home home = homeService.createHome(newHomeRequest);
        return ResponseEntity.ok(home);
    }

}
