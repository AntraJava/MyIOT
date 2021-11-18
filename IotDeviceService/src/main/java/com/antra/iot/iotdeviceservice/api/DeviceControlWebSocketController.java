package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.api.pojo.*;
import com.antra.iot.iotdeviceservice.service.DeviceControlService;
import com.antra.iot.iotdeviceservice.service.DeviceService;
import com.antra.iot.iotdeviceservice.service.feign.AuthServiceClient;
import com.antra.iot.iotdeviceservice.service.feign.HomeServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
public class DeviceControlWebSocketController {

    private final SimpMessagingTemplate messageTemplate;
    private final AuthServiceClient authServiceClient;
    private final DeviceService deviceService;
    private final HomeServiceClient homeServiceClient;
    private final DeviceControlService deviceControlService;

    public DeviceControlWebSocketController(SimpMessagingTemplate messageTemplate, AuthServiceClient authServiceClient, DeviceService deviceService, HomeServiceClient homeServiceClient, DeviceControlService deviceControlService) {
        this.messageTemplate = messageTemplate;
        this.authServiceClient = authServiceClient;
        this.deviceService = deviceService;
        this.homeServiceClient = homeServiceClient;
        this.deviceControlService = deviceControlService;
    }

    @EventListener
    public void handleSessionSubscribe(SessionSubscribeEvent event) throws IOException {
        Map<String,List<String>> stompHeader = ((Map<String,List<String>>)(event.getMessage().getHeaders().get("nativeHeaders")));
        String tokenStr = stompHeader.get("token").get(0);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(tokenStr);
        Customer customer = this.authServiceClient.verifyToken(jwtToken).getBody();
        String username = customer.getUsername(); //name , username , email, sub
        String homeId = stompHeader.get("homeId").get(0);
        log.info("WebSocket Subscribed by {} for homeId {}", username, homeId);
        sendHomeDeviceStatus(homeId);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        log.info("Websocket disconnected");
        log.info(event.getMessage().getPayload().toString());
    }
    @MessageMapping("/control")
    public void processMessageFromClient(@Payload DeviceControlWebSocketRequest message, @Header("token") String token){
        log.info("from device we got this : " + message);
        //verify token
        log.info("Verify device token : " + token);
        JwtToken tokenRequest = new JwtToken();
        tokenRequest.setToken(token);
        Customer currentCustomer = this.authServiceClient.verifyToken(tokenRequest).getBody();
        log.info("Token is valid for user {}", currentCustomer);
        //compare user id home id
        Home home = homeServiceClient.getHomeById(message.getHomeId()).getBody();
        if (Objects.isNull(currentCustomer) || Objects.isNull(home)) {
            log.error("Bad Request");
        }
        if (Objects.equals(currentCustomer.getId(), home.getOwnerId())) {
            log.info("Ready to control device {}", message.getDeviceId());
            List<DeviceVO> result = deviceControlService.processControl(message);
            this.messageTemplate.convertAndSend("/queue/home/" + message.getHomeId(), result);
        } else {
            log.error("Customer {} cannot control home {}", currentCustomer.getId(), home.getId());
        }
        //this.messageTemplate.convertAndSend("/queue/home/" + message.getHomeId(), new HashMap<String,String>(){{put("a","B");}});
    }

    private void sendHomeDeviceStatus(String homeId) {
        List<DeviceVO> devicesStatusList = this.deviceService.getDevicesStatusByHomeId(homeId);
        this.messageTemplate.convertAndSend("/queue/home/" + homeId, devicesStatusList);
    }

    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        log.error("Got error in websocket",exception);
        return exception.getMessage();
    }
}
