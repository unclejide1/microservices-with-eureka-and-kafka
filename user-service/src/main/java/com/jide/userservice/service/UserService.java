package com.jide.userservice.service;

import com.jide.userservice.Entity.User;

public interface UserService {
    User registerUser(User input);

    Iterable<User> findAll();
}
