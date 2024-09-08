package com.example.transactionhistorybackend.service.impl;

import com.example.transactionhistorybackend.model.User;
import com.example.transactionhistorybackend.repository.UserRepository;
import com.example.transactionhistorybackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String usernmae) {
        return userRepository.findByUsername(usernmae);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
