package com.jide.userservice.kafka.producer;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import javax.inject.Named;

@Slf4j
@Named
public class ApplicationEventServiceImpl implements ApplicationEventService {

    private Gson gson;
    private KafkaTemplate<String, String> kafkaTemplate;

    public ApplicationEventServiceImpl(Gson gson, KafkaTemplate<String, String> kafkaTemplate) {
        this.gson = gson;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishEvent(EventType eventType, EventModel<?> domain) {
        String payload = gson.toJson(domain.getData());
        log.info("{}: {}", eventType.getTopic(), payload);
        kafkaTemplate.send(eventType.getTopic(), payload);
    }
}
