package com.antra.iot.ioteventservice.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class EventSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topicName, Object message) {
        kafkaTemplate.send(topicName, message);
    }

    public void sendMessageAsync(String topicName, Object message) {
        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
