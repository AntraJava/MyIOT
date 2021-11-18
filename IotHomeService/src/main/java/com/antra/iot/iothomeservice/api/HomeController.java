package com.antra.iot.iothomeservice.api;

import com.antra.iot.iothomeservice.api.pojo.Home;
import com.antra.iot.iothomeservice.api.pojo.NewHomeRequest;
import com.antra.iot.iothomeservice.service.HomeService;
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

    @GetMapping("/{hId}")
    public ResponseEntity<Home> getHomeById(@PathVariable String hId) {
        Home home= homeService.getHome(hId);
        return ResponseEntity.ok(home);
    }

    @GetMapping("/config/{hId}")
    public ResponseEntity<Home> getHomeConfig(@PathVariable String hId) {
        return ResponseEntity.ok(homeService.getHomeDetailById(hId));
    }

    @GetMapping
    public ResponseEntity<List<Home>> getHomeConfigByCustomerId(@RequestHeader("userId")String userId) {
        List<Home> homeList = homeService.getHomeList(userId);
        return ResponseEntity.ok(homeList);
    }

    @PostMapping
    public ResponseEntity<Home> createNewHome(@Validated @RequestBody NewHomeRequest newHomeRequest, @RequestHeader("userId")String userId) {
        newHomeRequest.setOwnerId(userId);
        Home home = homeService.createHome(newHomeRequest);
        return ResponseEntity.ok(home);
    }

}
