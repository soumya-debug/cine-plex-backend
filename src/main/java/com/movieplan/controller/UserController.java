package com.movieplan.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplan.model.User;
import com.movieplan.model.UserLogin;
import com.movieplan.repository.userRepository;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	private userRepository uRepo;

	@PostMapping("/authenticate")
	public User authenticate(@RequestBody UserLogin UserLogin) {

		String email = UserLogin.getEmail();
		String password = UserLogin.getPassword();

		User theUser = uRepo.findByEmail(email);
		String Userpassword = theUser.getPassword();

		if (Userpassword.equals(password)) {
			System.out.println("login succesful");
		} else {
			throw new RuntimeException("password or email is incorrect");
		}
		return theUser;
	}

	@PostMapping("/signup")
	public User add(@RequestBody User theUser) {

		theUser.setId((long) 0);

		uRepo.save(theUser);

		return theUser;
	}

	@GetMapping("/verifyEmail/{email}")
	public ResponseEntity<User> verifyEmail(@PathVariable String email) {
		User user = uRepo.findByEmail(email);
		if (user != null) {
			return ResponseEntity.ok(user); // Return the user object if email exists
		} else {
			return ResponseEntity.notFound().build(); // Return 404 Not Found if email not found
		}
	}

	@PostMapping("/updatePassword/{userId}")
	public ResponseEntity<Map<String, String>> updatePassword(@PathVariable Long userId,
			@RequestBody String newPassword) {
		if (newPassword == null || newPassword.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(Collections.singletonMap("error", "New password is missing"));
		}

		Optional<User> optionalUser = uRepo.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setPassword(newPassword);
			uRepo.save(user);
			return ResponseEntity.ok(Collections.singletonMap("message", "Password updated successfully!"));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}