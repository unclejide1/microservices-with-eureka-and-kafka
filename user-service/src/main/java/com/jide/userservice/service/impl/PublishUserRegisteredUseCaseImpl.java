package com.jide.userservice.service.impl;

import com.jide.userservice.Entity.User;
import com.jide.userservice.kafka.event.UserCreatedEvent;
import com.jide.userservice.kafka.producer.ApplicationEventService;
import com.jide.userservice.kafka.producer.EventModel;
import com.jide.userservice.repository.UserRepository;
import com.jide.userservice.service.PublishUserRegisteredUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Named
public class PublishUserRegisteredUseCaseImpl implements PublishUserRegisteredUseCase {
    private ApplicationEventService applicationEventService;
    private UserRepository repository;
    @Override
    public void publishCreatedUser(Long id) {
        Optional<User> newUserOptional = repository.findById(id);

        if(!newUserOptional.isPresent()){
            throw  new RuntimeException("User doest exist");
        }
        User newUser = newUserOptional.get();
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .build();
        EventModel<UserCreatedEvent> eventModel = new EventModel<>(userCreatedEvent);
        applicationEventService.publishEvent(ApplicationEventService.EventType.USER_REGISTRATION, eventModel);
    }
}
