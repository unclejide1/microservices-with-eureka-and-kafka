package com.jide.userservice.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventModel<T> {
    private T data;
}
