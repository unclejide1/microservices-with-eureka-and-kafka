package com.jide.emailservice.kafka.receiver.events;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String username;

    private String password;



}
