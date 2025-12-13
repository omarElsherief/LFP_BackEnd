package com.zanta.lfp.user.service;


import com.zanta.lfp.user.Dto.UserDto;
import com.zanta.lfp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.zanta.lfp.user.enums.ERole.ADMIN;

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

    public ResponseEntity<?> createAdminUser(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setRole(ADMIN);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // return created DTO in body
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
