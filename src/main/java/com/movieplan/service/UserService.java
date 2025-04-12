package com.movieplan.service;

import com.movieplan.dto.UserLoginRequest;
import com.movieplan.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<?> authenticate(UserLoginRequest request);
    ResponseEntity<User> signUp(User user);
    ResponseEntity<User> verifyEmail(String email);
    ResponseEntity<Map<String, String>> updatePassword(Long userId, String newPassword);
}

