package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.api.pojo.Customer;
import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlWebSocketRequest;
import com.antra.iot.iotdeviceservice.api.pojo.Home;
import com.antra.iot.iotdeviceservice.service.feign.AuthServiceClient;
import com.antra.iot.iotdeviceservice.service.feign.HomeServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class DeviceControlWebSocketController {

    private final SimpMessagingTemplate messageTemplate;
    private final AuthServiceClient authServiceClient;
    private final HomeServiceClient homeServiceClient;

    public DeviceControlWebSocketController(SimpMessagingTemplate messageTemplate, AuthServiceClient authServiceClient, HomeServiceClient homeServiceClient) {
        this.messageTemplate = messageTemplate;
        this.authServiceClient = authServiceClient;
        this.homeServiceClient = homeServiceClient;
    }

    @MessageMapping("/control")
    public void processMessageFromClient(@Payload DeviceControlWebSocketRequest message, @Header("token") String token){
        log.info("from device we got this : " + message);
        //verify token
        log.info("Verify device token : " + token);
        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("token", token);
        Customer currentCustomer = this.authServiceClient.verifyToken(tokenRequest).getBody();
        log.info("Token is valid for user {}", currentCustomer);
        //compare user id home id
        Home home = homeServiceClient.getHomeById(message.getHomeId()).getBody();


        this.messageTemplate.convertAndSend("/queue/home/" + message.getHomeId(), new HashMap<String,String>(){{put("a","B");}});
    }

    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        log.error("Got error in websocket",exception);
        return exception.getMessage();
    }
}
