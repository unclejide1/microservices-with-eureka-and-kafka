package com.jide.userservice.service.impl;

import com.jide.userservice.Entity.User;
import com.jide.userservice.repository.UserRepository;
import com.jide.userservice.service.PublishUserRegisteredUseCase;
import com.jide.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private PublishUserRegisteredUseCase sender;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PublishUserRegisteredUseCase sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    @Override
    public User registerUser(User input) {
        User createdUser = userRepository.save(input);
        sender.publishCreatedUser(createdUser.getId());
        return createdUser;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
