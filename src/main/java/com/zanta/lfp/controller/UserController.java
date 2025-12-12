package com.zanta.lfp.controller;


import com.zanta.lfp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
