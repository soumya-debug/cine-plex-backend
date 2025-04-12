package com.movieplan.controller;

import com.movieplan.model.User;
import com.movieplan.model.UserLogin;
import com.movieplan.repository.UserRepository;
import com.movieplan.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody UserLogin loginRequest) {
		User user = userRepository.findByEmail(loginRequest.getEmail());

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "Email not found"));
		}

		if (!user.getPassword().equals(loginRequest.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "Incorrect password"));
		}

		String token = jwtUtil.generateToken(user.getEmail());
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("user", user);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) {
		user.setId(0L); // Ensure it's a new user
		User savedUser = userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@GetMapping("/verifyEmail/{email}")
	public ResponseEntity<User> verifyEmail(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@PostMapping("/updatePassword/{userId}")
	public ResponseEntity<Map<String, String>> updatePassword(
			@PathVariable Long userId,
			@RequestBody String newPassword) {

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
