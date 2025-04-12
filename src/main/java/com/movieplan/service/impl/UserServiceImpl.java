package com.movieplan.service.impl;

import com.movieplan.dto.UserLoginRequest;
import com.movieplan.model.User;
import com.movieplan.repository.UserRepository;
import com.movieplan.config.JwtUtil;
import com.movieplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> authenticate(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Email not found"));
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Incorrect password"));
        }

        String token = jwtUtil.generateToken(user.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<User> signUp(User user) {
        user.setId(0L);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @Override
    public ResponseEntity<User> verifyEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Map<String, String>> updatePassword(Long userId, String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Collections.singletonMap("error", "New password is missing"));
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        user.setPassword(newPassword);
        userRepository.save(user);

        return ResponseEntity.ok(Collections.singletonMap("message", "Password updated successfully!"));
    }
}
