package com.ayush.juryx.service;

import com.ayush.juryx.dto.RegisterRequest;
import com.ayush.juryx.model.Role;
import com.ayush.juryx.model.User;
import com.ayush.juryx.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        Role role = request.getRole() != null ? request.getRole() : Role.CLIENT;

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(role)
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
