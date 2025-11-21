package com.zanta.lfp.service;

import com.zanta.lfp.model.User;
import com.zanta.lfp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        checkUsernameAvailable(user.getUsername());
        checkEmailAvailable(user.getEmail());
        return userRepository.save(user);
    }

    private void checkUsernameAvailable(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }
    }

    private void checkEmailAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already exists");
        }
    }
}
