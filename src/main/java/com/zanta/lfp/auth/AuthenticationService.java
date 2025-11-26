package com.zanta.lfp.auth;


import com.zanta.lfp.config.JwtService;
import com.zanta.lfp.enums.ERole;
import com.zanta.lfp.model.User;
import com.zanta.lfp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        checkUsername(request.getUsername());
        checkEmail(request.getEmail());

        ERole roleToAssign = repository.count() == 0 ? ERole.ADMIN : ERole.USER;

        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleToAssign)
                .gender(request.getGender())
                .joinDate(LocalDateTime.now())
                .build();
        repository.save(user);
        return response(user);
    }
    private void checkUsername(String username){
        if (repository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
    }private void checkEmail(String email){
        if (repository.existsByEmail(email)) {
            throw new RuntimeException("Username already taken");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        return response(user);
    }
    private AuthenticationResponse response(User user){
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
