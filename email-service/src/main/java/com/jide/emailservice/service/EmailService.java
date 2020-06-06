package com.jide.emailservice.service;

import com.jide.emailservice.kafka.receiver.events.User;

public interface EmailService {
    void sendSimpleMessage(User input);
}
