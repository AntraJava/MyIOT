package com.example.iothomeservice.api;

import com.example.iothomeservice.api.pojo.Constants;
import com.example.iothomeservice.api.pojo.Device;
import com.example.iothomeservice.api.pojo.HomeConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
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
}
