package com.jide.userservice.kafka.event;


import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class UserCreatedEvent {
    private Long id;


    private String username;

    private String password;
}
