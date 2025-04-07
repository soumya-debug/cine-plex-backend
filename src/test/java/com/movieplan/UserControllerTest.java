package com.movieplan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.UserController;
import com.movieplan.model.User;
import com.movieplan.model.UserLogin;
import com.movieplan.repository.userRepository;

class UserControllerTest {

    private UserController userController;
    private userRepository uRepo;

    @BeforeEach
    void setUp() {
        uRepo = mock(userRepository.class);
        userController = new UserController();
        injectMockRepository(userController, uRepo);
    }

    @Test
    void testAuthenticateSuccess() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(uRepo.findByEmail(email)).thenReturn(user);

        UserLogin authuser = new UserLogin();
        authuser.setEmail(email);
        authuser.setPassword(password);

        User response = userController.authenticate(authuser);

        assertEquals(user, response);
    }

    @Test
    void testAuthenticateFailure() {
        String email = "test@example.com";

        when(uRepo.findByEmail(email)).thenReturn(null);

        UserLogin authuser = new UserLogin();
        authuser.setEmail(email);
        authuser.setPassword("incorrectpassword");

        assertThrows(RuntimeException.class, () -> {
            userController.authenticate(authuser);
        });
    }

    @Test
    void testSignUp() {
        User newUser = new User();
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password");

        when(uRepo.save(newUser)).thenReturn(newUser);

        User response = userController.add(newUser);

        assertEquals(newUser, response);
    }

    @Test
    void testVerifyEmailExists() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(uRepo.findByEmail(email)).thenReturn(user);

        ResponseEntity<User> response = userController.verifyEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testVerifyEmailNotFound() {
        String email = "nonexistent@example.com";

        when(uRepo.findByEmail(email)).thenReturn(null);

        ResponseEntity<User> response = userController.verifyEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Method to inject the mock repository using reflection
    private void injectMockRepository(UserController controller, userRepository repository) {
        try {
            Field field = controller.getClass().getDeclaredField("uRepo");
            field.setAccessible(true);
            field.set(controller, repository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
