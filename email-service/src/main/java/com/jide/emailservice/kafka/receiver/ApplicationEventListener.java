package com.jide.emailservice.kafka.receiver;

import com.google.gson.Gson;
import com.jide.emailservice.kafka.receiver.events.User;
import com.jide.emailservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import javax.inject.Named;

@Slf4j
@Named
public class ApplicationEventListener {
    private final String NEW_SMS_EVENT = "USER_CREATED_TOPIC";
    private final Gson gson;
    private EmailService emailService;


    public ApplicationEventListener(Gson gson, EmailService emailService) {
        this.gson = gson;
        this.emailService = emailService;
    }

    @KafkaListener(topics = {NEW_SMS_EVENT})
    public void listenForAccountNumberGenerationFailure(@Payload String payload) {
        log.info("Event-Topic:{}, payload: {}", NEW_SMS_EVENT, payload);
        User newUser = gson.fromJson(payload, User.class);
        emailService.sendSimpleMessage(newUser);
    }
}
