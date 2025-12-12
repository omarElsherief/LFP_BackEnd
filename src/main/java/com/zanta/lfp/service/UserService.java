package com.zanta.lfp.service;


import com.zanta.lfp.Dto.UserDto;
import com.zanta.lfp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> getAllUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(Map.of("users", users.stream()
                .map(UserDto::from)
                .toList()
        ));
    }


    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
