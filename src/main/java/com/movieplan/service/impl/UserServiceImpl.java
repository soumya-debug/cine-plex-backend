package com.movieplan.service.impl;

import com.movieplan.constants.MovieConstants;
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

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<Map<String, Object>> authenticate(UserLoginRequest request) {
        // Fetch user based on email
        User user = userRepository.findByEmail(request.getEmail());

        // Check if user exists
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap(MovieConstants.ERROR_KEY, "Email not found"));
        }

        // Check if password matches
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap(MovieConstants.ERROR_KEY, "Incorrect password"));
        }

        // Generate JWT token for the authenticated user
        String token = jwtUtil.generateToken(user.getEmail());

        // Prepare the response, including the token and user details
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);

        // Include only necessary user details (email and role) in the response
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("email", user.getEmail());
        userDetails.put("role", user.getRole());  // Assuming you have a role field in your User model

        response.put("user", userDetails);

        // Return response with token and user details
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
                    .body(Collections.singletonMap(MovieConstants.ERROR_KEY, "New password is missing"));
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