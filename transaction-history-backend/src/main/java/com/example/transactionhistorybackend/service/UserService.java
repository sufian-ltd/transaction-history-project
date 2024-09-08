package com.example.transactionhistorybackend.service;

import com.example.transactionhistorybackend.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String usernmae);
    void save(User user);
}
