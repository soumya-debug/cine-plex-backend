package com.movieplan.controller;

import com.movieplan.dto.UserLoginRequest;
import com.movieplan.model.User;
import com.movieplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UserLoginRequest loginRequest) {
		return userService.authenticate(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) {
		return userService.signUp(user);
	}

	@GetMapping("/verifyEmail/{email}")
	public ResponseEntity<User> verifyEmail(@PathVariable String email) {
		return userService.verifyEmail(email);
	}

	@PostMapping("/updatePassword/{userId}")
	public ResponseEntity<Map<String, String>> updatePassword(
			@PathVariable Long userId,
			@RequestBody String newPassword) {
		return userService.updatePassword(userId, newPassword);
	}
}
