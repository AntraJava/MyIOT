package com.antra.iot.iotdeviceservice.api;

import com.antra.iot.iotdeviceservice.api.pojo.DeviceControlWebSocketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
@Slf4j
public class DeviceControlWebSocketController {

    private final SimpMessagingTemplate messageTemplate;

    public DeviceControlWebSocketController(SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @MessageMapping("/control")
    public void processMessageFromClient(@Payload DeviceControlWebSocketRequest message, @Header("token") String token){
        log.info("from device we got this : " + message);
        log.info("from device token : " + token);
        //verify token
        //compare user id home id
        this.messageTemplate.convertAndSend("/queue/home/" + message.getHomeId(), new HashMap<String,String>(){{put("a","B");}});
    }

    @MessageExceptionHandler
    @SendToUser("/topic/errors")
    public String handleException(Throwable exception) {
        log.error("Got error in websocket",exception);
        return exception.getMessage();
    }
}
